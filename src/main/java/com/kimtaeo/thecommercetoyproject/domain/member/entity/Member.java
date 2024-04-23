package com.kimtaeo.thecommercetoyproject.domain.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID id;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String memberId;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String email;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(20)", nullable = false)
    private String phoneNumber;
}