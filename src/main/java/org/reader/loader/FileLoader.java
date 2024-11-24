package org.reader.loader;

import org.common.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class FileLoader implements Loader {

    // 'shelf' variable is a list that is presumed to contain xml files
    protected List<File> shelf;

    protected List<File> xmls = new ArrayList<>();

    // retrieve only XML files from list stored in the 'shelf' variable
    public void extractXmlFiles() {
        // if shelter directory is empty, just return it
        if(shelf.isEmpty()) {
            return;
        }

        for(File file : shelf) {
            if(!file.isFile()) {
                continue;
            }
            String ext = FileUtil.extractExtension(file);
            if(ext.equals("xml")) {
                xmls.add(file);
            }
        }
    }

    // read the directory using the provided path
    // return the files as a List type after reading them
    // if the directory is empty, simply return an empty list
    @Override
    public void load(String shelf) {};

    // Search for the XML file specified by the provided filename in the xmls list
    public abstract File select(String xml);

    public void showXMLFiles() {
        for(File file : xmls) {
            System.out.println("xml files " + file.getName());
        }
    }

}
