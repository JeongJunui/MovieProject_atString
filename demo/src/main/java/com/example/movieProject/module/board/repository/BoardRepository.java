package com.example.movieProject.module.board.repository;

import com.example.movieProject.module.board.entity.BoardCommentEntity;
import com.example.movieProject.module.board.entity.BoardEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Repository
@Mapper
public interface BoardRepository {

    BoardEntity getBoardByIdx(Integer boardIdx);
    List<BoardEntity> findBoard(String category, String keyField, String keyWord, Integer movieIdx);
    List<BoardEntity> findByUserId(@Param("userId") String userId);
    Integer insert(BoardEntity entity);
    Integer update(BoardEntity entity);
    Integer delete(Integer boardIdx);
    List<BoardCommentEntity> findComment(Integer boardIdx);
    Integer getCommentCount(Integer boardIdx);
    Integer insertComment(BoardCommentEntity entity);
    Integer deleteComment(Integer commentIdx);
    Integer pushLike(Integer boardIdx, String userId);
    Integer cancelLike(Integer boardIdx, String userId);
    Boolean hasLiked(Integer boardIdx, String userId);
    Integer checkLiked(Integer boardIdx);
}
