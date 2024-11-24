package org.reader.xml.parser;

import org.common.constants.InternalErrorCode;
import org.common.constants.XMLMarkerSet;
import org.common.constants.XMLTagType;
import org.common.utils.StringUtil;

public class GeneralXMLParser implements XMLParser{

    private final String xml;

    public GeneralXMLParser(String xml) {
        this.xml = xml;
    }

    @Override
    public void extractTag() {
        if(!validateXMLForm()) {
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

            valid = validateTagForm(refined);

            if(!valid) {
                throw new RuntimeException(InternalErrorCode.NOT_VALIDATED_XML.getMessage());
            }

            XMLTagType tagType = tagDiscriminator(refined);

            extractAttribute(refined);
        }
    }

    ///////////////////// PRIVATE METHOD /////////////////////
    private void extractAttribute(String line) {
        XMLTagType tagType = tagDiscriminator(line);

        for(int i = 0; i<line.length(); i++) {

        }
    }

    private XMLTagType tagDiscriminator(String line) {
        boolean hasStartMarker = line.charAt(1) == '?';
        boolean hasEndMarker = line.charAt(line.length()-2) == '?';


        if(hasStartMarker && hasEndMarker) {
            return XMLTagType.DECLARE;
        }
        return XMLTagType.CONTENT;
    }

    private boolean isOpen(char character) {
        return character == XMLMarkerSet.START_TAG.getChar();
    }

    private boolean isClose(char character) {
        return character == XMLMarkerSet.END_TAG.getChar();
    }

    ///////////////////// VALIDATE METHOD /////////////////////
    private boolean validateTagForm(String line) {
        boolean validOpenTag = false;
        boolean validCloseTag = false;

        for(int i = 0; i<line.length(); i++) {
            char c = line.charAt(i);
            if(i == 0) {
                validOpenTag = isOpen(c);
            }
            if(i == line.length()-1) {
                validCloseTag = isClose(c);
            }
        }

        return validOpenTag && validCloseTag;
    }

    // ToDo: 선언부 판별 태그는 정규식 활용 필요     
    private boolean validateXMLForm() {
        boolean valid = false;
        String[] lines = this.xml.split("\\n");

        for(int i = 0; i<lines.length; i++) {
            if(lines[i].isBlank()) {
                continue;
            }
            XMLTagType tagType = tagDiscriminator(lines[i].trim());
            if(i == 0 && tagType.equals(XMLTagType.DECLARE)) {
                int startMarkerCnt = StringUtil.countContainedString(lines[i].trim(), "\\?");
                valid = startMarkerCnt <= 2;

            }
            if(i > 0 && tagType.equals(XMLTagType.DECLARE)) {
                valid = false;
            }
        }

        return valid;

    }

    public void showXML() {
        System.out.println(xml);
    }

}
