package com.muscatlab.memberservice.application.domain.model;

import com.muscatlab.memberservice.common.BaseEntity;
import com.muscatlab.memberservice.utils.Crypto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Entity
@Table(name = "member", indexes = {@Index(name = "idx_member_id", columnList = "id")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Builder
    public Member(@NonNull String name, @NonNull String email,
                  @NonNull String password) {
        this.name = name;
        this.email = email;
        this.updatePassword(password);
    }

    public boolean validatePassword(String password) {
        return Crypto.encrypt(password, this.salt).equals(this.password);
    }

    public Member updatePassword(String newPassword) {
        this.salt = Crypto.getSalt();
        this.password = Crypto.encrypt(newPassword, this.salt);
        return this;
    }
}
