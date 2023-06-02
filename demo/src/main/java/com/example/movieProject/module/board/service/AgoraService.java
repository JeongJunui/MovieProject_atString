package com.example.movieProject.module.board.service;

import com.example.movieProject.common.ResDTO;
import com.example.movieProject.config.security.CustomUserDetails;
import com.example.movieProject.module.board.dto.AgoraDTO;
import com.example.movieProject.module.board.dto.AgoraDiscussDTO;
import com.example.movieProject.module.board.entity.AgoraDiscussEntity;
import com.example.movieProject.module.board.entity.AgoraEntity;
import com.example.movieProject.module.board.repository.AgoraRepository;

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
public class AgoraService {
    private final AgoraRepository agoraRepository;
    public HttpEntity<?> findAgora(String keyField, String keyWord)
    {
        List<AgoraEntity> agoraList=agoraRepository.findAgora(keyField, keyWord);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("검색완료")
                        .data(AgoraDTO.ResBasic.fromEntityList(agoraList))
                        .build(),
                HttpStatus.OK
        );
    }
    public HttpEntity<?> getAgora(Integer agoraIdx)
    {
        AgoraEntity agoraEntity=agoraRepository.getAgora(agoraIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("토론글 불러오기")
                        .data(AgoraDTO.ResBasic.fromAEntity(agoraEntity))
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> createAgora(CustomUserDetails customUserDetails, AgoraDTO.ReqBasic reqBasic)
    {
        agoraRepository.createAgora(reqBasic.toEntity(customUserDetails.getUserEntity().getIdx));
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("토론글 작성 성공")
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> deleteAgora(CustomUserDetails customUserDetails, Integer agoraIdx)
    {
        AgoraEntity agoraEntity=agoraRepository.getAgora(agoraIdx);
        if(agoraEntity.getUserId()!=customUserDetails.getUserEntity.getUserId())
        {
            throw new BadRequestException("잘못된 요청입니다");
        }
        agoraRepository.deleteAgora(agoraIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("게시글 삭제 성공")
                        .build(),
                HttpStatus.OK
        );
    }

    public HttpEntity<?> getDiscuss(Integer agoraIdx)
    {
        List<AgoraDiscussEntity> commentList=agoraRepository.getDiscuss(agoraIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("검색완료")
                        .data(AgoraDiscussDTO.ResBasic.fromEntityList(commentList))
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> insertDiscuss(CustomUserDetails customUserDetails, AgoraDiscussDTO.ReqBasic reqBasic)
    {
        agoraRepository.insertDiscuss(reqBasic.toEntity(customUserDetails.getUserEntity().getIdx));
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("댓글 작성 성공")
                        .build(),
                HttpStatus.OK
        );
    }
    @Transactional
    public HttpEntity<?> deleteDiscuss(CustomUserDetails customUserDetails, Integer commentIdx)
    {
        AgoraDiscussEntity commentEntity=agoraRepository.getADiscuss(commentIdx);
        if(commentEntity.getUserId()!=customUserDetails.getUserEntity.getUserId())
        {
            throw new BadRequestException("잘못된 요청입니다");
        }
        agoraRepository.deleteDiscuss(commentIdx);
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("댓글 삭제 성공")
                        .build(),
                HttpStatus.OK
        );
    }
}
