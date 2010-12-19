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
package org.statmantis.tools.javadoc;

import java.util.Map;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.ProgramElementDoc;
import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

/**
 * Taglet that supports the linkhref inline tag. This tag will return just the
 * HREF to a javadoc class file (not any of the methods/fields)
 * 
 * @author Chad Retz
 */
public class LinkHrefTaglet implements Taglet {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void register(Map tagletMap) {
        LinkHrefTaglet tag = new LinkHrefTaglet();
        Taglet t = (Taglet) tagletMap.get(tag.getName());
        if (t != null) {
            tagletMap.remove(tag.getName());
        }
        tagletMap.put(tag.getName(), tag);
    }

    @Override
    public String getName() {
        return "linkhref";
    }

    @Override
    public boolean inConstructor() {
        return true;
    }

    @Override
    public boolean inField() {
        return true;
    }

    @Override
    public boolean inMethod() {
        return true;
    }

    @Override
    public boolean inOverview() {
        return true;
    }

    @Override
    public boolean inPackage() {
        return true;
    }

    @Override
    public boolean inType() {
        return true;
    }

    @Override
    public boolean isInlineTag() {
        return true;
    }

    private PackageDoc getPackageDoc(Tag tag) {
        Doc holder = tag.holder();
        if (holder instanceof ProgramElementDoc) {
            return ((ProgramElementDoc) holder).containingPackage();
        } else if (holder instanceof PackageDoc) {
            return (PackageDoc) holder;
        } else {
            throw new RuntimeException("Unrecognized holder: " + holder);
        }
    }

    private ClassDoc getTopLevelClassDoc(ClassDoc classDoc) {
        if (classDoc.containingClass() == null) {
            return classDoc;
        } else {
            return getTopLevelClassDoc(classDoc);
        }
    }

    private ClassDoc getTopLevelClassDoc(Tag tag) {
        Doc holder = tag.holder();
        if (holder instanceof PackageDoc) {
            return null;
        } else if (holder instanceof ClassDoc) {
            return getTopLevelClassDoc((ClassDoc) holder);
        } else if (holder instanceof ProgramElementDoc) {
            return getTopLevelClassDoc(((ProgramElementDoc) holder)
                    .containingClass());
        } else {
            throw new RuntimeException("Unrecognized holder: " + holder);
        }
    }

    private ClassDoc findClass(String className, ClassDoc[] classImports) {
        for (ClassDoc classDoc : classImports) {
            if (classDoc.name().equals(className)) {
                return classDoc;
            }
        }
        return null;
    }

    private ClassDoc findClass(String className, PackageDoc... packageImports) {
        for (PackageDoc packageDoc : packageImports) {
            for (ClassDoc found : packageDoc.allClasses(true)) {
                if (found.name().equals(className)) {
                    return found;
                }
            }
        }
        return null;
    }
    
    private String error(Tag tag, String error) {
        System.err.println(tag.position() + ": warning - " + error);
        return "javascript: //error";
    }

    @Override
    @SuppressWarnings("deprecation")
    public String toString(Tag tag) {
        PackageDoc packageDoc = getPackageDoc(tag);
        ClassDoc topLevelClassDoc = getTopLevelClassDoc(tag);
        //k, what I'm gonna do is what the main one does...go up to the root
        StringBuilder href = new StringBuilder();
        int dotIndex = packageDoc.name().indexOf('.');
        while (dotIndex != -1) {
            href.append("../");
            dotIndex = packageDoc.name().indexOf('.', dotIndex + 1);
        }
        //package name is empty when it is the root package
        if (!packageDoc.name().isEmpty()) {
            href.append("../");
        }
        //now that we have the root, begin the string parse
        String classInTag = tag.text();
        int poundIndex = classInTag.indexOf('#');
        if (poundIndex != -1) {
            classInTag = classInTag.substring(0, poundIndex);
        }
        //ok, if it's qualified, we just assume it's all good
        if (classInTag.indexOf('.') == -1) {
            ClassDoc classDoc;
            if (topLevelClassDoc == null) {
                //not in a class scope? just try inside this package
                classDoc = findClass(classInTag, packageDoc);
                if (classDoc == null) {
                    //they should qualify it then
                    return error(tag, "Can't locate linkhref class " + classInTag + 
                            ". The name should be qualified.");
                }
            } else {
                //nope? ok, first try my inner classes
                classDoc = findClass(classInTag, topLevelClassDoc.innerClasses(true));
                if (classDoc == null) {
                    //nope? ok, try my single-type-imports
                    classDoc = findClass(classInTag,
                            topLevelClassDoc.importedClasses());
                    if (classDoc == null) {
                        //nope? ok, try my type-import-on-demands
                        classDoc = findClass(classInTag, topLevelClassDoc.importedPackages());
                        if (classDoc == null) {
                            //nope? ok, finally try my own package
                            findClass(classInTag, topLevelClassDoc.containingPackage());
                            if (classDoc == null) {
                                //not even now? well, just assume it's there because
                                //  javadoc doesn't populate fairly
                                classInTag = topLevelClassDoc.containingPackage().name() +
                                        '.' + classInTag;
                            }
                        }
                    }
                }
            }
            if (classDoc != null) {
                classInTag = classDoc.qualifiedName();
            }
        }
        if (classInTag.indexOf('.') == -1) {
            return error(tag, "Unable get linkhref for class " + classInTag +
                    " because it is in the root package");
        }
        // ok, now make the link by replacing the dots w/ slashes
        href.append(classInTag.replace('.', '/'));
        // add .html
        href.append(".html");
        // all good
        return href.toString();
    }

    @Override
    public String toString(Tag[] tags) {
        // not for inline tags...nope
        return null;
    }

}
