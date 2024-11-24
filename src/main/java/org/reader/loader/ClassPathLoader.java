package org.reader.loader;

import org.common.constants.InternalErrorCode;
import org.example.Main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClassPathLoader extends FileLoader implements Loader {

    private final ClassLoader root;

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
        super.shelf = files.map(Arrays::asList).orElseGet(ArrayList::new);
    }

    public List<File> extractXmlFiles() {
        // if shelter directory is empty, just return it
        return super.extractXmlFiles();
    }

}
