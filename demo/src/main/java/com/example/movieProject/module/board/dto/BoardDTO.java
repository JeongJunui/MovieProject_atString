package com.example.movieProject.module.board.dto;

import com.example.movieProject.module.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class BoardDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqBasic {
        @NotBlank(message = "카테고리를 선택하시오")
        private String category;

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
}
