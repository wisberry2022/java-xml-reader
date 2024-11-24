package org.reader.xml.parser;

import javax.sql.rowset.spi.XmlReader;
import java.util.Map;

public class GeneralXMLParser implements XMLParser{

    private final String xml;

    public GeneralXMLParser(String xml) {
        this.xml = xml;
    }

    @Override
    public void extractTag() {
        String[] line = this.xml.split("\\n");
        for(String str : line) {
            if(str.isBlank()) {
                continue;
            }
            extractAttribute(str);
        }
    }

    private void extractAttribute(String line) {

    }


    public void showXML() {
        System.out.println(xml);
    }

}
