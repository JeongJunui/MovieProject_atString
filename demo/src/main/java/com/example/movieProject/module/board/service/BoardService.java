package com.example.movieProject.module.board.service;

import com.example.movieProject.common.ResDTO;
import com.example.movieProject.module.board.dto.BoardDTO;
import com.example.movieProject.module.board.entity.BoardEntity;
import com.example.movieProject.module.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
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
                        .data(BoardDTO.)
        )
    }

}
