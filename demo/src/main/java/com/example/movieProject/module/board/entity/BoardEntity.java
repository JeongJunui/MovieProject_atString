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
public class BoardEntity {
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

    public void setLiked(Integer liked) { this.liked=liked; }
    public void setFilename(String filename) { this.filename=filename; }
    public void setFilesize(Integer filesize) { this.filesize=filesize; }
}
