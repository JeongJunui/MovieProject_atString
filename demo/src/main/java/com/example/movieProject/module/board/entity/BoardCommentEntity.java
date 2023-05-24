package com.example.movieProject.module.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentEntity {
    private Integer cidx;
    private Integer boardIdx;
    private String userId;
    private String detail;
    private Date postedDate;
    public void setDetail(String detail) { this.detail=detail; }
}
