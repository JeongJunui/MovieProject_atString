package com.example.movieProject.module.board.dto;

import com.example.movieProject.module.board.entity.BoardLikeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BoardLikeDTO {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqBasic {

        private Integer boardIdx;

        public BoardLikeEntity toEntity(String userId)
        {
            return BoardLikeEntity.builder()
                    .boardIdx(boardIdx)
                    .userId(userId)
                    .build();
        }
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResBasic {
        private List<BoardLike> boardLikeList;
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        static class BoardLike {
            Integer likeIdx;
            Integer boardIdx;
            String userId;
            Date likedDate;
        }

        public static ResBasic fromEntityList(List<BoardLikeEntity> boardLikeEntityList) {
            List<BoardLike> boardLikeList = boardLikeEntityList.stream().map(boardLikeEntity -> {
                return BoardLike.builder()
                        .likeIdx(boardLikeEntity.getLikeIdx())
                        .boardIdx(boardLikeEntity.getBoardIdx())
                        .userId(boardLikeEntity.getUserId())
                        .likedDate(boardLikeEntity.getLikedDate())
                        .build();
            }).collect(Collectors.toList());

            return new ResBasic(boardLikeList);
        }
    }
}
