package com.example.movieProject.module.board.repository;

import com.example.movieProject.module.board.entity.AgoraDiscussEntity;
import com.example.movieProject.module.board.entity.AgoraEntity;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Repository
@Mapper
public interface AgoraRepository {
    AgoraEntity getAgora(Integer agoraIdx);
    List<AgoraEntity> findAgora(String keyField, String keyWord);
    Integer getAgoraCount(String keyField, String keyWord);
    Integer createAgora(AgoraEntity entity);
    Integer deleteAgora(Integer agoraIdx);
    List<AgoraDiscussEntity> getDiscuss(Integer agoraIdx);
    AgoraDiscussEntity getADiscuss(Integer discussIdx);
    Integer getDiscussCount(Integer agoraIdx);
    Integer insertDiscuss(AgoraDiscussEntity entity);
    Integer deleteDiscuss(Integer discussIdx);
}
