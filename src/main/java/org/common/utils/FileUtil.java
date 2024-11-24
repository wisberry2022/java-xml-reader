package org.common.utils;

import org.common.constants.InternalErrorCode;

import java.io.File;

public class FileUtil {

    public static String extractExtension(File file) {
        String fileName = file.getName();
        String[] parsed = fileName.split("\\.");

        if(parsed.length != 2) {
            throw new IllegalArgumentException(InternalErrorCode.NOT_QUALIFIED_FILE.getMessage());
        }

        return parsed[1];
    }

}
