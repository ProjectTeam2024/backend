package kr.project.backend.exception;


public enum CommonErrorCode {
    // common
    SUCCESS("SUCCESS", "정상처리"),
    FAIL("FAIL", "실패"),
    COMMON_FAIL("A000", "실패"),
    NULL_DATA("A001","데이터가 없습니다."),
    NOT_ALLOW_FILE("A002","허용되지 않는 파일입니다."),
    NOT_FOUND_FILE("A003","파일을 찾을 수 없습니다."),

    // token
    EXPIRED_TOKEN("BOOO","만료된 토큰입니다."),
    WRONG_TOKEN("B001","형식이 잘못된 토큰입니다."),
    NOT_EXIST_TOKEN("B002","존재하지 않는 토큰입니다."),
    NOT_FOUND_TOKEN("B003","토큰정보를 찾을 수 없습니다."),

    // user
    NOT_FOUND_USER("C000","회원을 찾을 수 없습니다."),
    ALREADY_DROP_USER("C001","이미 탈퇴된 회원입니다."),
    JOIN_TERM_DATE("C002","탈퇴 30일후 재가입 가능합니다."),
    ALREADY_JOIN_USER("C003","이미 가입된 회원입니다."),
    NOT_FOUND_FAVORITE("C004","없는 즐겨찾기 정보입니다."),

    // coin
    NOT_FOUND_COIN("D000","없는 코인정보입니다.");
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
