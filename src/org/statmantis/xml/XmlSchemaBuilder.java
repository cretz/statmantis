/*
 * Copyright 2010 Chad Retz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.statmantis.xml;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.statmantis.BaseRuntimeException;
import org.statmantis.annotation.XmlInternalProperty;
import org.statmantis.annotation.XmlTopLevel;
import org.statmantis.model.Game;
import org.statmantis.util.ClassesInPackage;
import org.statmantis.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.common.primitives.Primitives;

/**
 * Creates schema from the model POJOs
 *
 * @author Chad Retz
 */
public class XmlSchemaBuilder {
    
    private static final String XML_SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";

    public Document buildXmlSchema(String namespace, boolean everythingOptional) {
        //make the structure
        Document doc;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            throw new BaseRuntimeException(e);
        }
        Element schema = doc.createElement("schema");
        schema.setAttribute("xmlns", XML_SCHEMA_NAMESPACE);
        schema.setAttribute("targetNamespace", namespace);
        schema.setAttribute("elementFormDefault", "qualified");
        schema.setAttribute("xmlns:bb", namespace);
        doc.appendChild(schema);
        //we loop through each class and make complex types
        for (Class<?> clazz : Game.class.getPackage().getAnnotation(
                ClassesInPackage.class).value()) {
            if (!clazz.isInterface()) {
                for (Element element : createComplexTypeFromClass(clazz, doc, 
                        namespace, everythingOptional)) {
                    schema.appendChild(element);
                }
            }
        }
        //return
        return doc;
    }

    private Element[] createComplexTypeFromClass(Class<?> clazz, Document doc, 
            String namespace, boolean everythingOptional) {
        if (clazz.isEnum()) {
            //this is a simple type extending string w/ an enumeration restriction
            Element simpleType = doc.createElement("simpleType");
            simpleType.setAttribute("name", StringUtils.toCamelCase(clazz.getSimpleName()));
            //restriction
            Element restriction = doc.createElement("restriction");
            restriction.setAttribute("base", "string");
            //add all enum values
            for (Object constant : clazz.getEnumConstants()) {
                Element enumeration = doc.createElement("enumeration");
                enumeration.setAttribute("value", constant.toString());
                restriction.appendChild(enumeration);
            }
            simpleType.appendChild(restriction);
            return new Element[] { simpleType };
        } else {
            //all fields as either attributes or elements
            Element complexType = doc.createElement("complexType");
            complexType.setAttribute("name", StringUtils.toCamelCase(clazz.getSimpleName()));
            Element choice = doc.createElement("choice");
            choice.setAttribute("minOccurs", "0");
            choice.setAttribute("maxOccurs", "unbounded");
            complexType.appendChild(choice);
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                //first, does it have an xml internal property?
                Class<?> type = Primitives.wrap(field.getType());
                //grab column reference to check nullable
                Column column = field.getAnnotation(Column.class);
                //check internal
                XmlInternalProperty internal = field.getAnnotation(XmlInternalProperty.class);
                if (internal != null) {
                    //k, it's a long then
                    Element attribute = doc.createElement("attribute");
                    attribute.setAttribute("name", internal.value());
                    attribute.setAttribute("type", getXmlType(Long.class));
                    if (!everythingOptional && column != null && !column.nullable()) {
                        attribute.setAttribute("use", "required");
                    }
                    complexType.appendChild(attribute);
                } else if (!Collection.class.isAssignableFrom(type) &&
                        (!type.getPackage().equals(clazz.getPackage()) ||
                                type.isEnum())) {
                    //attribute worthy
                    Element attribute = doc.createElement("attribute");
                    attribute.setAttribute("name", field.getName());
                    attribute.setAttribute("type", getXmlType(type));
                    if (!everythingOptional && column != null && !column.nullable()) {
                        attribute.setAttribute("use", "required");
                    }
                    complexType.appendChild(attribute);
                } else {
                    Element element = doc.createElement("element");
                    if (column == null || column.nullable()) {
                        element.setAttribute("minOccurs", "0");
                    } else {
                        element.setAttribute("minOccurs", "1");
                    }
                    if (Collection.class.isAssignableFrom(type)) {
                        //collection? k, get the parameter type and work w/ that
                        type = Primitives.wrap((Class<?>) ((ParameterizedType) field.getGenericType()).
                                getActualTypeArguments()[0]);
                        element.setAttribute("maxOccurs", "unbounded");
                    }
                    element.setAttribute("name", field.getName());
                    element.setAttribute("type", getXmlType(type));
                    choice.appendChild(element);
                }
            }
            if (!choice.hasChildNodes()) {
                //no children? remove the choice
                complexType.removeChild(choice);
            }
            //top level?
            if (clazz.isAnnotationPresent(XmlTopLevel.class)) {
                Element element = doc.createElement("element");
                String name = StringUtils.toCamelCase(clazz.getSimpleName());
                element.setAttribute("name", name);
                element.setAttribute("type", "bb:" + name);
                return new Element[] { element, complexType };
            } else {
                return new Element[] { complexType };
            }
        }
    }
    
    private String getXmlType(Class<?> clazz) {
        if (clazz.equals(String.class)) {
            return "string";
        } else if (clazz.equals(Long.class)) {
            return "long";
        } else if (clazz.equals(Integer.class)) {
            return "int";
        } else if (clazz.equals(Boolean.class)) {
            return "boolean";
        } else if (clazz.equals(Float.class)) {
            return "float";
        } else if (clazz.equals(BigDecimal.class)) {
            return "decimal";
        } else if (clazz.equals(Date.class)) {
            return "date";
        } else if (clazz.equals(Time.class)) {
            return "time";
        } else if (clazz.equals(Timestamp.class)) {
            return "dateTime";
        } else {
            return "bb:" + StringUtils.toCamelCase(clazz.getSimpleName());
        }
    }
}
