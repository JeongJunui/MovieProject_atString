
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
public class BoardLikeEntity {
    private Integer likeIdx;
    private Integer boardIdx;
    private String userId;
    private Date likedDate;
}
