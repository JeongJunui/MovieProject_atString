<<<<<<<< HEAD:demo/src/main/java/com/example/movieProject/module/board/BoardLikeDTO.java
package com.example.movieProject.module.board;
========
package com.example.movieProject.module.board.entity;
>>>>>>>> 0b361bf7f6446238e5bfe82dd3a4894702cb5a7c:demo/src/main/java/com/example/movieProject/module/board/entity/BoardLikeEntity.java

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardLikeEntity {
    private Integer likeIdx;
    private Integer boardIdx;
    private String userId;
    private Date likedDate;
}
