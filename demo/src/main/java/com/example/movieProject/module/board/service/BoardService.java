package com.example.movieProject.module.board.service;

import com.example.movieProject.common.ResDTO;
import com.example.movieProject.config.security.CustomUserDetails;
import com.example.movieProject.module.board.dto.BoardCommentDTO;
import com.example.movieProject.module.board.dto.BoardDTO;
import com.example.movieProject.module.board.entity.BoardEntity;
import com.example.movieProject.module.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    public HttpEntity<?> findBoard(String category, String keyField, String keyWord, Integer movieIdx)
    {
        List<BoardEntity> boardList=boardRepository.findBoard(category, keyField, keyWord, movieIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("검색완료")
                        .data(BoardDTO.ResBasic.fromEntityList(boardList))
                        .build(),
                HttpStatus.OK
        );
    }
    public HttpEntity<?> findByUserId(String userId)
    {
        List<BoardEntity> boardList=boardRepository.findByUserId(userId);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("검색완료")
                        .data(BoardDTO.ResBasic.fromEntityList(boardList))
                        .build(),
                HttpStatus.OK
        );
    }

    public HttpEntity<?> getBoardByIdx(Integer boardIdx)
    {
        BoardEntity boardEntity=boardRepository.getBoardByIdx(boardIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("게시글 불러오기")
                        .data(BoardDTO.ResBasic.fromAEntity(boardEntity))
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> insert(CustomUserDetails customUserDetails, BoardDTO.ReqBasic reqBasic)
    {
        boardRepository.insert(reqBasic.toEntity(customUserDetails.getUserEntity().getIdx));
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("게시글 작성 성공")
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> update(CustomUserDetails customUserDetails, BoardDTO.ReqBasic reqBasic, Integer boardIdx)
    {
        BoardEntity boardEntity=boardRepository.getBoardByIdx(boardIdx);
        if(boardEntity.getUserId()!=customUserDetails.getUserEntity.getUserId())
        {
            throw new BadRequestException("잘못된 요청입니다");
        }
        boardRepository.update(reqBasic.toEntity(customUserDetails.getUserEntity().getIdx));
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("게시글 수정 성공")
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> delete(CustomUserDetails customUserDetails, Integer boardIdx)
    {
        BoardEntity boardEntity=boardRepository.getBoardByIdx(boardIdx);
        if(boardEntity.getUserId()!=customUserDetails.getUserEntity.getUserId())
        {
            throw new BadRequestException("잘못된 요청입니다");
        }
        boardRepository.delete(boardIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("게시글 삭제 성공")
                        .build(),
                HttpStatus.OK
        );
    }

    public HttpEntity<?> findComment(Integer boardIdx)
    {
        List<BoardCommentEntity> commentList=boardRepository.findComment(boardIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("검색완료")
                        .data(BoardCommentDTO.ResBasic.fromEntityList(commentList))
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> insertComment(CustomUserDetails customUserDetails, BoardCommentDTO.ReqBasic reqBasic)
    {
        boardRepository.insertComment(reqBasic.toEntity(customUserDetails.getUserEntity().getIdx));
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("댓글 작성 성공")
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> deleteComment(CustomUserDetails customUserDetails, Integer commentIdx)
    {
        BoardCommentEntity commentEntity=boardRepository.getComment(commentIdx);
        if(commentEntity.getUserId()!=customUserDetails.getUserEntity.getUserId())
        {
            throw new BadRequestException("잘못된 요청입니다");
        }
        boardRepository.deleteComment(commentIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("댓글 삭제 성공")
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> pushLike(CustomuserDetails customuserDetails, Integer boardIdx)
    {
        String userId=customUserDetails.getUserEntity.getUserId();
        if(hasLiked(boardIdx, userId))
        {
            throw new BadRequestException("잘못된 요청입니다");
        }
        boardRepository.pushLike(boardIdx, userId);
        boardRepository.checkLiked(boardIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("좋아요 반영 성공")
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> cancelLike(CustomuserDetails customuserDetails, Integer boardIdx)
    {
        String userId=customUserDetails.getUserEntity.getUserId();
        if(!hasLiked(boardIdx, userId))
        {
            throw new BadRequestException("잘못된 요청입니다");
        }
        boardRepository.cancelLike(boardIdx, userId);
        boardRepository.checkLiked(boardIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("좋아요 반영 성공")
                        .build(),
                HttpStatus.OK
        );
    }
}
