package com.example.movieProject.module.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Integer boardIdx;
    private String userId;
    private String category;
    private String title;
    private String detail;
    private Integer movieIdx;
    private Integer liked;
    private Date postedDate;
    private String filename;
    private Integer filesize;
}
