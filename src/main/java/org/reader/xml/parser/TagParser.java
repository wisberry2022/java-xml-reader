package org.reader.xml.parser;

import org.common.constants.XMLMarkerSet;
import org.common.constants.XMLTagType;
import org.common.utils.StringUtil;

public class TagParser {

    private static TagParser instance;

    private String xml;

    private TagParser(String xml) {
        this.xml = xml;
    }

    public static TagParser getInstance(String xml) {
        if (instance == null) {
            instance = new TagParser(xml);
            return instance;
        }
        return instance;
    }

    // 사용
    public void extractAttribute(String line) {
        XMLTagType tagType = tagDiscriminator(line);

        for(int i = 0; i<line.length(); i++) {

        }
    }

    // 사용
    public XMLTagType tagDiscriminator(String line) {
        String xmlDeclarationPattern = "^<\\?xml\\s+version=\"1\\.0\"\\s+encoding=\"UTF-8\"\\s*\\?>$";

        if(line.matches(xmlDeclarationPattern)) {
            return XMLTagType.DECLARE;
        }
        return XMLTagType.CONTENT;
    }

    // 사용
    public boolean validateTagForm(String line) {
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

    // 사용
    public boolean validateXMLForm() {
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

    /////////////// PRIVATE METHOD ///////////////
    private boolean isOpen(char character) {
        return character == XMLMarkerSet.START_TAG.getChar();
    }

    private boolean isClose(char character) {
        return character == XMLMarkerSet.END_TAG.getChar();
    }

}
