package kr.project.backend.exception;

public enum CommonErrorCode {
    // common
    COMMON_FAIL("A000", "실패"),
    // token
    EXPIRED_TOKEN("BOOO","만료된 토큰입니다."),
    WRONG_TOKEN("B001","형식이 잘못된 토큰입니다."),
    NOT_EXIST_TOKEN("B002","존재하지 않는 토큰입니다."),
    // user
    NOT_FOUND_USER("C000","회원을 찾을 수 없습니다.")
    ;



    private final String code;
    private final String message;

    CommonErrorCode(final String code, final String message) {
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
