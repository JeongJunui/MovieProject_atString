package com.example.movieProject.module.board.dto;

import com.example.movieProject.module.board.entity.AgoraEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AgoraDTO {

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

        public AgoraEntity toEntity(String userId)
        {
            return AgoraEntity.builder()
                    .userId(userId)
                    .movieIdx(movieIdx)
                    .agoraTitle(title)
                    .agoraDetail(detail)
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
        private List<Agora> agoraList;
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        static class Agora {
            Integer agoraIdx;
            String userId;
            Integer movieIdx;
            String agoraTitle;
            String agoraDetail;
            Date postedDate;
            String filename;
            Integer filesize;
        }

        public static ResBasic fromEntityList(List<AgoraEntity> agoraEntityList) {
            List<Agora> agoraList = agoraEntityList.stream().map(agoraEntity -> {
                return Agora.builder()
                        .agoraIdx(agoraEntity.getAgoraIdx())
                        .userId(agoraEntity.getUserId())
                        .movieIdx(agoraEntity.getMovieIdx())
                        .agoraTitle(agoraEntity.getAgoraTitle())
                        .agoraDetail(agoraEntity.getAgoraDetail())
                        .postedDate(agoraEntity.getPostedDate())
                        .filename(agoraEntity.getFilename())
                        .filesize(agoraEntity.getFilesize())
                        .build();
            }).collect(Collectors.toList());

            return new ResBasic(agoraList);
        }
        public static ResBasic fromAEntity(AgoraEntity agoraEntity)
        {
            return new ResBasic((List<Agora>) Agora.builder()
                    .agoraIdx(agoraEntity.getAgoraIdx())
                    .userId(agoraEntity.getUserId())
                    .movieIdx(agoraEntity.getMovieIdx())
                    .agoraTitle(agoraEntity.getAgoraTitle())
                    .agoraDetail(agoraEntity.getAgoraDetail())
                    .postedDate(agoraEntity.getPostedDate())
                    .filename(agoraEntity.getFilename())
                    .filesize(agoraEntity.getFilesize())
                    .build());
        }

    }
}
