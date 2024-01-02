package kr.project.backend.exception;


public enum CommonErrorCode {
    // common
    SUCCESS("SUCCESS", "정상처리"),
    FAIL("FAIL", "실패"),
    COMMON_FAIL("A000", "실패"),

    // token
    EXPIRED_TOKEN("BOOO","만료된 토큰입니다."),
    WRONG_TOKEN("B001","형식이 잘못된 토큰입니다."),
    NOT_EXIST_TOKEN("B002","존재하지 않는 토큰입니다."),
    NOT_FOUND_TOKEN("B003","토큰정보를 찾을 수 없습니다."),
    // user
    NOT_FOUND_USER("C000","회원을 찾을 수 없습니다."),
    ALREADY_DROP_USER("C001","이미 탈퇴된 회원입니다."),
    JOIN_TERM_DATE("C002","탈퇴 30일후 재가입 가능합니다.");
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
