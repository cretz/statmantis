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
package org.statmantis.util;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.statmantis.BaseRuntimeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * DOM utilities
 *
 * @author Chad Retz
 */
public final class DomUtils {

    public static String documentToString(Document doc, boolean indent) {
        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new BaseRuntimeException(e);
        }
        if (indent) {
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        }
        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(doc);
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new BaseRuntimeException(e);
        }
        return result.getWriter().toString();
    }
    
    public static String getElementNameSansNamespace(Element element) {
        String name = element.getNodeName();
        int colonIndex = name.indexOf(':');
        if (colonIndex != -1) {
            return name.substring(colonIndex + 1);
        }
        return name;
    }
    
    private DomUtils() {
    }
}
