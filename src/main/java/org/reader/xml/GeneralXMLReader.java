package org.reader.xml;

import org.reader.loader.ClassPathLoader;
import org.reader.loader.FileLoader;

import java.io.File;
import java.util.List;

public class GeneralXMLReader implements XMLReader {

    private FileLoader loader;

    public GeneralXMLReader() {
        loader = new ClassPathLoader();
        loader.load("org/shelf");
    }

    public GeneralXMLReader(String path) {
        this();
        loader.load(path);
    }

    @Override
    public void read(String fileName) {
//        List<File> = loader.extractXmlFiles();
    }


}
