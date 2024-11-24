package org.reader.loader;

import org.common.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class FileLoader implements Loader {

    // 'shelf' variable is a list that is presumed to contain xml files
    protected List<File> shelf;

    public List<File> extractXmlFiles() {
        // if shelter directory is empty, just return it
        if(shelf.isEmpty()) {
            return shelf;
        }

        List<File> xmls = new ArrayList<>();

        for(File file : shelf) {
            if(!file.isFile()) {
                continue;
            }
            String ext = FileUtil.extractExtension(file);
            if(ext.equals("xml")) {
                xmls.add(file);
            }
        }

        return xmls;
    }

    @Override
    public void load(String dirname) {};

}
