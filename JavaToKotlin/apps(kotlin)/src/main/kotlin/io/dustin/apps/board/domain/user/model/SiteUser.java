package io.dustin.apps.board.domain.user.model;

import io.dustin.apps.common.code.YesOrNo;
import io.dustin.apps.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SiteUser extends BaseEntity {

    @Builder
    public SiteUser (Long id, @NotNull String nickName, @NotNull String password, @NotNull String email, String profile, YesOrNo isBaned, YesOrNo isDeleted,
                     Long follower,Long following
                     ) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.profile = profile;
        this.follower = follower;
        this.following = following;
        this.isBaned = isBaned == null ? YesOrNo.N : isBaned;
        this.isDeleted = isDeleted == null ? YesOrNo.N : isDeleted;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickName;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_baned", length = 1)
    private YesOrNo isBaned;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted", length = 1)
    private YesOrNo isDeleted;

    private String password;

    private String profile;
    
    private Long follower;

    private Long following;
    

    public void updatePassword(String password) { this.password = password; }

    public void updateEmail(String email) { this.email = email; }

    public void updateNickName(String nickName) { this.nickName = nickName; }

    public void updateProfile(String profile) { this.profile = profile; }

    /** 탈퇴 후 30일이 경과시 삭제되는 기능 구현 필요 */
    public void deleteUser() {
        this.isDeleted = YesOrNo.Y;
    }

    public void banedUser() {
        this.isBaned = YesOrNo.Y;
    }

}
