package com.example.movieProject.module.board.dto;

import com.example.movieProject.module.board.entity.BoardCommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BoardCommentDTO {


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqBasic {

        private Integer boardIdx;
        @NotBlank(message = "내용을 입력하시오")
        private String detail;

        public BoardCommentEntity toEntity(String userId)
        {
            return BoardCommentEntity.builder()
                    .boardIdx(boardIdx)
                    .userId(userId)
                    .detail(detail)
                    .build();
        }
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResBasic {
        private List<BoardComment> boardCommentList;
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        static class BoardComment {
            Integer cidx;
            Integer boardIdx;
            String userId;
            String detail;
            Date postedDate;
        }

        public static ResBasic fromEntityList(List<BoardCommentEntity> boardCommentEntityList) {
            List<BoardComment> boardCommentList = boardCommentEntityList.stream().map(boardCommentEntity -> {
                return BoardComment.builder()
                        .cidx(boardCommentEntity.getCidx())
                        .boardIdx(boardCommentEntity.getBoardIdx())
                        .userId(boardCommentEntity.getUserId())
                        .detail(boardCommentEntity.getDetail())
                        .postedDate(boardCommentEntity.getPostedDate())
                        .build();
            }).collect(Collectors.toList());

            return new ResBasic(boardCommentList);
        }
    }
}
