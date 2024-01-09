package kr.project.backend.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.project.backend.converter.BooleanToYNConverter;
import kr.project.backend.entity.coin.StakingInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;


import java.util.UUID;

@Getter
@DynamicInsert
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @NotNull
    @Comment(value = "즐겨찾기 키값")
    private UUID favoriteId;

    @Column(columnDefinition = "char default 'N'")
    @Convert(converter = BooleanToYNConverter.class)
    private boolean delYn;

    @ManyToOne
    @JoinColumn(name = "stakingInfo_id")
    private StakingInfo stakingInfo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Favorite(User userInfo, StakingInfo stakingInfo) {
        this.stakingInfo = stakingInfo;
        this.user = userInfo;
    }

    public void unFavorite() {
        this.delYn = true;
    }
}
