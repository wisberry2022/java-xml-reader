package org.example;

import org.reader.loader.ClassPathLoader;
import org.reader.loader.DirectoryLoader;
import org.reader.loader.FileLoader;
import org.reader.loader.Loader;
import org.reader.xml.GeneralXMLReader;
import org.reader.xml.XMLReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) throws IOException {
        XMLReader reader = new GeneralXMLReader();
        reader.read("introduce.xml");

//        FileLoader loader = new DirectoryLoader();
//        XMLReader reader1 = new GeneralXMLReader(loader, "java-project/xml-reader/src/main/resources/org/shelf");
//        reader1.read("introduce.xml");
    }
}