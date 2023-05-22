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
public class AgoraEntity {
    private Integer agoraIdx;
    private String userId;
    private int movieIdx;
    private String agoraTitle;
    private String agoraDetail;
    private Date postedDate;
    private String filename;
    private Integer filesize;
}
