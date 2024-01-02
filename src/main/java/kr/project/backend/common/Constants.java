package kr.project.backend.common;

public class Constants {

    /** 회원상태 코드 */
    public static final class USER_STATE {
        public static final String ACTIVE_USER = "01"; //정상

        public static final String REST_USER = "02"; //휴면

        public static final String LOCK_USER = "03"; //잠김

        public static final String DROP_USER = "04"; //탈퇴
    }


}
