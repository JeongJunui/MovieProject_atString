<<<<<<<< HEAD:demo/src/main/java/com/example/movieProject/module/board/BoardCommentDTO.java
package com.example.movieProject.module.board;
========
package com.example.movieProject.module.board.entity;
>>>>>>>> 0b361bf7f6446238e5bfe82dd3a4894702cb5a7c:demo/src/main/java/com/example/movieProject/module/board/entity/BoardCommentEntity.java

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
