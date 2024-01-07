package kr.project.backend.common;

public class Constants {

    /** 회원상태 코드 */
    public static final class USER_STATE {

        public static final String CODE = "USER_STATE";

        public static final String ACTIVE_USER = "01";

        public static final String REST_USER = "02";

        public static final String LOCK_USER = "03";

        public static final String DROP_USER = "04";
    }

    /** 회원가입 구분 코드 */
    public static final class USER_JOIN_SNS_KIND {

        public static final String CODE = "USER_JOIN_SNS_KIND";

        public static final String KAKAO = "01";

        public static final String NAVER = "02";

        public static final String GOOGLE = "03";

        public static final String APPLE = "04";
    }

    /** 회원가입 os 구분 코드 */
    public static final class USER_JOIN_OS_KIND {

        public static final String CODE = "USER_JOIN_OS_KIND";

        public static final String AOS = "01";

        public static final String IOS = "02";

        public static final String WINDOW = "03";

        public static final String MAC = "04";
    }


}
