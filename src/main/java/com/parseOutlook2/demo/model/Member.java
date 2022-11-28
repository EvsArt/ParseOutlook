package com.parseOutlook2.demo.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
@Table(name = "members")
@RequiredArgsConstructor
public class Member {

    @Id
//    @SequenceGenerator(name = "members_seq", sequenceName = "MEMBERS_SEQ", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private String email;

    private String name;

    private String surname;

    private String patronymic;

    private String role;

    private String info;

    private String university;

    private String division;

    private String faculty;

    private String specialization;

    private Short course;

//    private Short group;

}