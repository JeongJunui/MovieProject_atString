<<<<<<<< HEAD:demo/src/main/java/com/example/movieProject/module/board/BoardDTO.java
package com.example.movieProject.module.board;
========
package com.example.movieProject.module.board.entity;
>>>>>>>> 0b361bf7f6446238e5bfe82dd3a4894702cb5a7c:demo/src/main/java/com/example/movieProject/module/board/entity/QnaAnswerEntity.java

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
