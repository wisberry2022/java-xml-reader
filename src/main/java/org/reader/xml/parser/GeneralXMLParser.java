package org.reader.xml.parser;

import org.common.constants.InternalErrorCode;
import org.common.constants.XMLTagType;

public class GeneralXMLParser implements XMLParser{

    private TagParser tagParser;
    private final String xml;

    public GeneralXMLParser(String xml) {
        this.xml = xml;
        tagParser = TagParser.getInstance(xml);
    }

    @Override
    public void extractTag() {
        if(!tagParser.validateXMLForm()) {
            throw new RuntimeException(InternalErrorCode.NOT_VALIDATED_XML.getMessage());
        }

        String[] line = this.xml.split("\\n");
        boolean valid;
        for(int i = 0; i<line.length; i++) {
            String str = line[i];
            if(str.isBlank()) {
                continue;
            }
            String refined = str.trim();

            valid = tagParser.validateTagForm(refined);

            if(!valid) {
                throw new RuntimeException(InternalErrorCode.NOT_VALIDATED_XML.getMessage());
            }

            XMLTagType tagType = tagParser.tagDiscriminator(refined);

            tagParser.extractAttribute(refined);
        }
    }

    public void showXML() {
        System.out.println(xml);
    }

}
