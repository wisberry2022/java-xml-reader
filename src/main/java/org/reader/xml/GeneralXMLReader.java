package org.reader.xml;

import org.reader.loader.ClassPathLoader;
import org.reader.loader.FileLoader;

import java.io.*;

public class GeneralXMLReader implements XMLReader {

    private final FileLoader loader;

    public GeneralXMLReader() {
        loader = new ClassPathLoader();
        loader.load("org/shelf");
    }

    public GeneralXMLReader(String path) {
        this();
        loader.load(path);
    }

    public GeneralXMLReader(FileLoader loader) {
        this.loader = loader;
        loader.load("org/shelf");
    }

    public GeneralXMLReader(FileLoader loader, String path) {
        this.loader = loader;
        loader.load(path);
    }

    @Override
    public void read(String xml) throws IOException {
        loader.extractXmlFiles();
        File file = loader.select(xml);

        try (FileReader reader = new FileReader(file.getPath())) {
            BufferedReader br = new BufferedReader(reader);

            StringBuilder message = new StringBuilder();
            String line;
            while (true) {
                line = br.readLine();
                if(line == null) break;
                message.append(line).append("\n");
            }

            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
