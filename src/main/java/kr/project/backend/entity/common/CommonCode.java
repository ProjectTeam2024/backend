package kr.project.backend.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import kr.project.backend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonCode extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "공통코드키값")
    private Long commonCodeId;

    @Comment(value = "그룹 공통코드")
    private String grpCommonCode;

    @Comment(value = "공통코드")
    private String commonCode;

    @Comment(value = "공통코드명")
    private String commonCodeName;

}
