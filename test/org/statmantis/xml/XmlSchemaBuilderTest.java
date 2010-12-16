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

import org.junit.Test;
import org.statmantis.util.DomUtils;
import org.statmantis.xml.XmlSchemaBuilder;

/**
 * Tests for the schema builder
 *
 * @author Chad Retz
 */
public class XmlSchemaBuilderTest {

    @Test
    public void testXmlSchemaBuilder() {
        //let's just build the thing and dump it to make sure it works
        System.out.println(DomUtils.documentToString(new XmlSchemaBuilder().
                buildXmlSchema("http://base.googlecode.com/schema/1", true), true));
    }
}
