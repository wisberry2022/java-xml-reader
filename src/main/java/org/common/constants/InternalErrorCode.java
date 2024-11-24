package org.common.constants;

public enum InternalErrorCode {

    // Util Error Code
    NOT_QUALIFIED_FILE("NOT_QUALIFIED_FILE", "유효한 파일이 아닙니다."),

    // Loader Error Code
    NOT_EXISTED_DIRECTORY("NOT_EXISTED_DIRECTORY", "존재하지 않은 디렉토리입니다."),
    NOT_DIRECTORY("NOT_DIRECTORY", "디렉토리가 아닙니다."),
    NOT_EXISTED_FILE("NOT_EXISTED_FILE", "존재하지 않는 파일입니다.");

    private String code;
    private String message;

    InternalErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
