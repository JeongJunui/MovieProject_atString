package com.example.movieProject.module.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private String userid;
    private String usernn;
    private String userpwd;
    private int birth;
    private boolean gender;
}
