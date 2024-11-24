package org.reader.loader;

import java.io.File;
import java.net.URL;
import java.util.List;

public interface Loader {

    void load(String dirName);

    List<File> extractXmlFiles();

}
