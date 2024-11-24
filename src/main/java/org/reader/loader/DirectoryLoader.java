package org.reader.loader;

import org.common.constants.InternalErrorCode;
import org.common.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoryLoader implements Loader {

    private String root = "D:/";

    // 'shelf' variable is a list that is presumed to contain xml files
    private List<File> shelf;

    public DirectoryLoader() {

    }

    public DirectoryLoader(String root) {
        this.root = root;
    }

    // read the directory using the provided path
    // return the files as a List type after reading them
    // if the directory is empty, simply return an empty list
    @Override
    public void load(String dirName) {
        String url = getFullDir(dirName);
        File dir = new File(url);

        // When the directory is empty, throw IllegalArgumentException
        if(!dir.isDirectory()) {
           throw new IllegalArgumentException(InternalErrorCode.NOT_DIRECTORY.getMessage());
        }

        File[] files = dir.listFiles();

        // When the directory contains no files, return an empty List
        if(files == null) {
            shelf = new ArrayList<>();
            return;
        }

        // assign a list of files obtained by reading the directory to the 'shelf' variable
        shelf = Arrays.asList(files);
    }

    @Override
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

    // Return the full url as a 'String' type.
    // If the root directory path is not initialized, just return the base URL.
    // If a custom path is initialized, return the url concatenated with '/' followed by the custom path
    private String getFullDir(String dirName) {
        if(root.equals("D:/")) {
            return root.concat(dirName);
        }
        return root.concat("/").concat(dirName);
    }

}
