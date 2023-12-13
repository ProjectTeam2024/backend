package kr.project.backend.common;

import lombok.Data;

/**
 * 회원정보
 * @author kh
 * @version v1.0
 */
@Data
public class User {

    /** 회원관리번호 */
    private String mgtId;

    /** eamil */
    private String email;

    /** 이름 */
    private String mmbrNm;

    /** 패스워드 */
    private String password;

}
