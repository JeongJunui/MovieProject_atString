package com.example.movieProject.module.board.dto;

import com.example.movieProject.module.board.entity.AgoraDiscussEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AgoraDiscussDTO {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqBasic {

        @NotBlank(message = "내용을 입력하시오")
        private String detail;
        private Integer agoraIdx;

        public AgoraDiscussEntity toEntity(String userId)
        {
            return AgoraDiscussEntity.builder()
                    .agoraIdx(agoraIdx)
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
        private List<AgoraDiscuss> agoraDetailList;
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        static class AgoraDiscuss {
            Integer discussIdx;
            Integer agoraIdx;
            String userId;
            String detail;
            Date postedDate;
        }

        public static ResBasic fromEntityList(List<AgoraDiscussEntity> agoraDiscussEntityList) {
            List<AgoraDiscuss> agoraDiscussList = agoraDiscussEntityList.stream().map(agoraDiscussEntity -> {
                return AgoraDiscuss.builder()
                        .discussIdx(agoraDiscussEntity.getDiscussIdx())
                        .agoraIdx(agoraDiscussEntity.getAgoraIdx())
                        .userId(agoraDiscussEntity.getUserId())
                        .detail(agoraDiscussEntity.getDetail())
                        .postedDate(agoraDiscussEntity.getPostedDate())
                        .build();
            }).collect(Collectors.toList());

            return new ResBasic(agoraDiscussList);
        }
    }
}
