package com.example.movieProject.module.board.dto;

import com.example.movieProject.module.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


public class BoardDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqBasic {

        @NotBlank(message = "제목을 입력하시오")
        private String title;

        @NotBlank(message = "내용을 입력하시오")
        private String detail;

        private Integer movieIdx;

        private String filename;
        private Integer filesize;

        public BoardEntity toEntity(String userId)
        {
            return BoardEntity.builder()
                    .userId(userId)
                    .category(category)
                    .title(title)
                    .detail(detail)
                    .movieIdx(movieIdx)
                    .liked(0)
                    .filename(filename)
                    .filesize(filesize)
                    .build();
        }
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResBasic {
        private List<Board> boardList;
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        static class Board {
            Integer boardIdx;
            String userId;
            String category;
            String title;
            String detail;
            Integer movieIdx;
            Integer liked;
            Date postedDate;
            String filename;
            Integer filesize;
        }

        public static ResBasic fromEntityList(List<BoardEntity> boardEntityList) {
            List<Board> boardList = boardEntityList.stream().map(boardEntity -> {
                return Board.builder()
                        .boardIdx(boardEntity.getBoardIdx())
                        .userId(boardEntity.getUserId())
                        .category(boardEntity.getCategory())
                        .title(boardEntity.getTitle())
                        .detail(boardEntity.getDetail())
                        .movieIdx(boardEntity.getMovieIdx())
                        .liked(boardEntity.getLiked())
                        .postedDate(boardEntity.getPostedDate())
                        .filename(boardEntity.getFilename())
                        .filesize(boardEntity.getFilesize())
                        .build();
            }).collect(Collectors.toList());

            return new ResBasic(boardList);
        }
        public static ResBasic fromAEntity(BoardEntity boardEntity)
        {
            return new ResBasic((List<Board>) Board.builder()
                    .boardIdx(boardEntity.getBoardIdx())
                    .userId(boardEntity.getUserId())
                    .category(boardEntity.getCategory())
                    .title(boardEntity.getTitle())
                    .detail(boardEntity.getDetail())
                    .movieIdx(boardEntity.getMovieIdx())
                    .liked(boardEntity.getLiked())
                    .postedDate(boardEntity.getPostedDate())
                    .filename(boardEntity.getFilename())
                    .filesize(boardEntity.getFilesize())
                    .build());
        }
    }
}
