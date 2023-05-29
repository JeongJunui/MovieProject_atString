package com.example.movieProject.module.board.dto;

import com.example.movieProject.module.board.entity.QnaAnswerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class QnaAnswerDTO {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqBasic {

        private Integer qnaIdx;
        @NotBlank(message = "제목을 입력하시오")
        private String title;

        @NotBlank(message = "내용을 입력하시오")
        private String detail;

        private String filename;
        private Integer filesize;

        public QnaAnswerEntity toEntity(String userId)
        {
            return QnaAnswerEntity.builder()
                    .qnaIdx(qnaIdx)
                    .userId(userId)
                    .title(title)
                    .detail(detail)
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
        private List<QnaAnswer> qnaAnswerList;
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        static class QnaAnswer {
            Integer answerIdx;
            Integer qnaIdx;
            String userId;
            String title;
            String detail;
            Date postedDate;
            String filename;
            Integer filesize;
        }

        public static ResBasic fromEntityList(List<QnaAnswerEntity> qnaAnswerEntityList) {
            List<QnaAnswer> qnaAnswerList = qnaAnswerEntityList.stream().map(qnaAnswerEntity -> {
                return QnaAnswer.builder()
                        .answerIdx(qnaAnswerEntity.getAnswerIdx())
                        .qnaIdx(qnaAnswerEntity.getQnaIdx())
                        .userId(qnaAnswerEntity.getUserId())
                        .title(qnaAnswerEntity.getTitle())
                        .detail(qnaAnswerEntity.getDetail())
                        .postedDate(qnaAnswerEntity.getPostedDate())
                        .filename(qnaAnswerEntity.getFilename())
                        .filesize(qnaAnswerEntity.getFilesize())
                        .build();
            }).collect(Collectors.toList());

            return new ResBasic(qnaAnswerList);
        }
    }
}
