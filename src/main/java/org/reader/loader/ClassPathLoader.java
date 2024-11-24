package org.reader.loader;

import org.common.constants.InternalErrorCode;
import org.common.utils.FileUtil;
import org.example.Main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClassPathLoader implements Loader {

    private final ClassLoader root;

    // 'shelf' variable is a list that is presumed to contain xml files
    private List<File> shelf;

    public ClassPathLoader() {
        root = Main.class.getClassLoader();
    }

    // read the directory using the provided path
    // return the files as a List type after reading them
    // if the directory is empty, simply return an empty list
    @Override
    public void load(String dirName) {
        Optional<URL> dir = Optional.ofNullable(root.getResource(dirName));

        // When the target directory is empty, throw RuntimeException
        if(dir.isEmpty()) {
          throw new RuntimeException(InternalErrorCode.NOT_EXISTED_DIRECTORY.getMessage());
        }

        URL url = dir.get();
        Optional<File[]> files = Optional.ofNullable(new File(url.getFile()).listFiles());

        // assign a list of files obtained by reading the directory to the 'shelf' variable
        shelf = files.map(Arrays::asList).orElseGet(ArrayList::new);
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

}
