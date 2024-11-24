package org.reader.loader;

import org.common.constants.InternalErrorCode;
import org.common.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoryLoader extends FileLoader {

    private String root = "D:/";

    public DirectoryLoader() {

    }

    public DirectoryLoader(String root) {
        this.root = root;
    }

    @Override
    public void load(String shelf) {
        String url = getFullDir(shelf);
        File dir = new File(url);

        // When the directory is empty, throw IllegalArgumentException
        if(!dir.isDirectory()) {
           throw new IllegalArgumentException(InternalErrorCode.NOT_DIRECTORY.getMessage());
        }

        File[] files = dir.listFiles();

        // When the directory contains no files, return an empty List
        if(files == null) {
            super.shelf = new ArrayList<>();
            return;
        }

        // assign a list of files obtained by reading the directory to the 'shelf' variable
        super.shelf = Arrays.asList(files);
    }

    @Override
    public File select(String xml) {
        return super.xmls
                .stream()
                .filter(file -> file.getName().equals(xml))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(InternalErrorCode.NOT_EXISTED_FILE.getMessage()));
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
