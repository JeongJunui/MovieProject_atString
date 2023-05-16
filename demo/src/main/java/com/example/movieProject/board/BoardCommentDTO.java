package com.example.movieProject.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentDTO {
    private Integer cidx;
    private Integer boardIdx;
    private String userId;
    private String detail;
    private Date postedDate;
}
