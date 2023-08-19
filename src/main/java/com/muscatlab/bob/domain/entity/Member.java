package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
@Table(schema = "bob", name = "member", indexes = {@Index(name = "idx_member", columnList = "id", unique = true)})
public class Member extends BaseEntity {
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Builder
    public Member(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    public boolean validate(String password) {
        return this.password.equals(password);
    }
}
