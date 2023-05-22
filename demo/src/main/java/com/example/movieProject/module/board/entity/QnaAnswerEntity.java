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
public class QnaAnswerEntity {
    private Integer answerIdx;
    private Integer qnaIdx;
    private String userId;
    private String title;
    private String detail;
    private Date postedDate;
    private String filename;
    private Integer filesize;
}
