package com.example.movieProject.module.board.dto;

import com.example.movieProject.module.board.entity.QnaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class QnaDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqBasic {

        @NotBlank(message = "제목을 입력하시오")
        private String title;

        @NotBlank(message = "내용을 입력하시오")
        private String detail;

        private boolean isSecret;

        private String filename;
        private Integer filesize;

        public QnaEntity toEntity(String userId)
        {
            return QnaEntity.builder()
                    .userId(userId)
                    .title(title)
                    .detail(detail)
                    .isSecret(isSecret)
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
        private List<Qna> qnaList;
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        static class Qna {
            Integer qnaIdx;
            String userId;
            String title;
            String detail;
            Date postedDate;
            boolean isSecret;
            String filename;
            Integer filesize;
        }

        public static ResBasic fromEntityList(List<QnaEntity> qnaEntityList) {
            List<Qna> qnaList = qnaEntityList.stream().map(qnaEntity -> {
                return Qna.builder()
                        .qnaIdx(qnaEntity.getQnaIdx())
                        .userId(qnaEntity.getUserId())
                        .title(qnaEntity.getTitle())
                        .detail(qnaEntity.getDetail())
                        .postedDate(qnaEntity.getPostedDate())
                        .isSecret(qnaEntity.isSecret())
                        .filename(qnaEntity.getFilename())
                        .filesize(qnaEntity.getFilesize())
                        .build();
            }).collect(Collectors.toList());

            return new ResBasic(qnaList);
        }
    }
}
