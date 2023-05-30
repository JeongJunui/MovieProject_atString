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
public class AgoraDTO {
    private Integer agoraIdx;
    private String userId;
    private int movieIdx;
    private String agoraTitle;
    private String agoraDetail;
    private Date postedDate;
    private String filename;
    private Integer filesize;
}
