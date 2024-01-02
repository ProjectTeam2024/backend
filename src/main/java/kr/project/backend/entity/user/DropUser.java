package kr.project.backend.entity.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.project.backend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DropUser extends BaseTimeEntity implements Serializable {

    /**
     * 탈퇴키값
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @NotNull
    @Schema(description = "탈퇴키값", example = "3fdec9bc-1592-4e53-97e6-3454869f5f95")
    private UUID dropId;

    /**
     * cino
     */
    @Schema(description = "cino", example = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJhZGI1Y2M5Yy01NDVmLTR==")
    private String userCino;

    /**
     * 탈퇴일자
     */
    @Schema(description = "탈퇴일시", example = "2023-01-01 02:12:00")
    private String dropDttm;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Schema(hidden = true)
    private User user;

    public DropUser(String userCino, User userInfo) {
        this.userCino = userCino;
        this.dropDttm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.user = userInfo;
    }
}
