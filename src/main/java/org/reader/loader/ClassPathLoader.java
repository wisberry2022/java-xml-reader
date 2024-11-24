package org.reader.loader;

import org.common.constants.InternalErrorCode;
import org.example.Main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClassPathLoader extends FileLoader {

    private final ClassLoader root;

    public ClassPathLoader() {
        root = Main.class.getClassLoader();
    }

    @Override
    public void load(String shelf) {
        Optional<URL> dir = Optional.ofNullable(root.getResource(shelf));

        // When the target directory is empty, throw RuntimeException
        if(dir.isEmpty()) {
          throw new RuntimeException(InternalErrorCode.NOT_EXISTED_DIRECTORY.getMessage());
        }

        URL url = dir.get();
        Optional<File[]> files = Optional.ofNullable(new File(url.getFile()).listFiles());

        // assign a list of files obtained by reading the directory to the 'shelf' variable
        super.shelf = files.map(Arrays::asList).orElseGet(ArrayList::new);
    }

    @Override
    public File select(String xml) {
        return super.xmls
                .stream()
                .filter(file -> file.getName().equals(xml))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(InternalErrorCode.NOT_EXISTED_FILE.getMessage()));
    }

}
