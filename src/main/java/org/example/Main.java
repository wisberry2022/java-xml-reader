package org.example;

import org.reader.loader.ClassPathLoader;
import org.reader.loader.DirectoryLoader;
import org.reader.loader.Loader;

import java.io.File;
import java.util.List;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        Loader loader = new DirectoryLoader();
        List<File> files = loader.load("java-project/xml-reader/src/main/resources/org/shelter");

        for(File file: files) {
            System.out.println(file);
        }
    }
}