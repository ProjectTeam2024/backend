package kr.project.backend.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import kr.project.backend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonCode extends BaseTimeEntity implements Serializable {

    /**
     * 그룹 공통코드
     */
    @Id
    @Schema(description = "그룹 공통코드", example = "USER_STATE")
    private String grpCommonCode;

    /**
     * 공통코드
     */
    //@Id
    @Schema(description = "공통코드", example = "01")
    private String commonCode;

    /**
     * 공통코드
     */
    @Schema(description = "공통코드명", example = "정상")
    private String commonCodeName;

}
