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
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.statmantis.BaseRuntimeException;
import org.statmantis.annotation.XmlInternalProperty;
import org.statmantis.model.Game;
import org.statmantis.util.CollectionUtils;
import org.statmantis.util.ConvertUtils;
import org.statmantis.util.DomUtils;
import org.statmantis.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Maps;

/**
 * Unmarshals XML into Java objects
 *
 * @author Chad Retz
 */
public class XmlUnmarshaller {

    @SuppressWarnings("unchecked")
    public <T> T unmarshal(Element element) {
        Class<T> retClass;
        try {
            retClass = (Class<T>) Class.forName(
                    Game.class.getPackage().getName() + "." + 
                    StringUtils.capitalize(DomUtils.
                            getElementNameSansNamespace(element)));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return unmarshal(element, retClass);
    }

    @SuppressWarnings("unchecked")
    public <T> T unmarshal(Element element, Class<T> retClass) {
        try {
            //ok, let's loop through the fields and do some reflection work
            T ret = retClass.newInstance();
            //go through the fields and get the xml internal properties
            Map<String, Field> internalProperties = Maps.newHashMap();
            for (Field field : retClass.getDeclaredFields()) {
                XmlInternalProperty property = field.getAnnotation(XmlInternalProperty.class);
                if (property != null) {
                    field.setAccessible(true);
                    internalProperties.put(property.value(), field);
                }
            }
            //go through attributes and populate
            NamedNodeMap attributes = element.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node node = attributes.item(i);
                //if there's a prefix, we skip
                if (node.getNodeName().indexOf(':') != -1) {
                    continue;
                }
                try {
                    Field standardField = retClass.getDeclaredField(node.getNodeName());
                    standardField.setAccessible(true);
                    standardField.set(ret, ConvertUtils.convertKnownType(
                            node.getNodeValue(), standardField.getType()));
                } catch (NoSuchFieldException e) {
                    //better have an xml internal property
                    Field internalField = internalProperties.get(node.getNodeName());
                    if (internalField == null) {
                        throw new BaseRuntimeException("Unknown field: " + node.getNodeName());
                    }
                    Object object = internalField.getType().newInstance();
                    try {
                        Field toSet = object.getClass().getDeclaredField(node.getNodeName());
                        toSet.setAccessible(true);
                        toSet.set(object, ConvertUtils.convertKnownType(
                                node.getNodeValue(), toSet.getType()));
                    } catch (NoSuchFieldException e1) {
                        throw new BaseRuntimeException("Unknown field mapping: " + node.getNodeName());
                    }
                    internalField.set(ret, object);
                }
            }
            //now go through all elements
            NodeList children = element.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (!(child instanceof Element)) {
                    continue;
                }
                Element childElement = (Element) child;
                String childElementName = DomUtils.getElementNameSansNamespace(childElement);
                //first, we check to see if it's an internal field
                Field internalField = internalProperties.get(childElementName);
                if (internalField != null) {
                    //we've got a collection folks...
                    internalField.setAccessible(true);
                    Collection<Object> collection = (Collection<Object>) internalField.get(ret);
                    if (collection == null) {
                        collection = (Collection<Object>) CollectionUtils.newCollectionFromInterface(
                                (Class<Collection<Object>>) internalField.getType());
                        internalField.set(ret, collection);
                    }
                    Object object = internalField.getType().newInstance();
                    try {
                        Field toSet = object.getClass().getDeclaredField(childElementName);
                        toSet.setAccessible(true);
                        toSet.set(object, ConvertUtils.convertKnownType(
                                childElement.getTextContent(), toSet.getType()));
                    } catch (NoSuchFieldException e) {
                        throw new BaseRuntimeException("Unknown field mapping: " + 
                                childElementName);
                    }
                    collection.add(object);
                } else {
                    //so it must be a field...
                    try {
                        internalField = retClass.getDeclaredField(childElementName);
                        internalField.setAccessible(true);
                    } catch (Exception e) {
                        throw new BaseRuntimeException("Unknown field mapping: " + 
                                childElementName);
                    }
                    //collection?
                    if (!Collection.class.isAssignableFrom(internalField.getType())) {
                        //nope, simple object, so just set it
                        internalField.set(ret, unmarshal(childElement, internalField.getType()));
                    } else {
                        //yup
                        Collection<Object> collection = (Collection<Object>) internalField.get(ret);
                        if (collection == null) {
                            collection = (Collection<Object>) CollectionUtils.newCollectionFromInterface(
                                    (Class<Collection<Object>>) internalField.getType());
                            internalField.set(ret, collection);
                        }
                        //we also need the collection type
                        Class<?> collectionClass = (Class<?>) ((ParameterizedType) internalField.
                                getGenericType()).getActualTypeArguments()[0];
                        collection.add(unmarshal(childElement, collectionClass));
                    }
                }
            }
            return ret;
        } catch (InstantiationException e) {
            throw new BaseRuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new BaseRuntimeException(e);
        }
    }
    
    public void joinTogetherReferencedObjects(Object subject, List<Object> topLevelPossibles) {
        //Map<Class<?>, Map<String, Field>> internalProperties = new HashMap<Class<?>, Map<String,Field>>();
        throw new UnsupportedOperationException();
    }
}
