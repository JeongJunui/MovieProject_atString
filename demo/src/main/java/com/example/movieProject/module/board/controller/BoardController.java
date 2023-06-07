package com.example.movieProject.module.board.controller;

import com.example.movieProject.config.security.CustomUserDetails;
import com.example.movieProject.module.board.dto.BoardCommentDTO;
import com.example.movieProject.module.board.dto.BoardDTO;
import com.example.movieProject.module.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping
    public HttpEntity<?> search(
            String category, String keyField, String keyWord, Integer movieIdx)
    {
        log.info("목록 불러옴");
        return boardService.findBoard(category, keyField, keyWord, movieIdx);
    }
    @GetMapping
    public HttpEntity<?> load(
            Integer boardIdx)
    {
        log.info("게시글 불러옴");
        return boardService.getBoardByIdx(boardIdx);
    }
    @PostMapping
    public HttpEntity<?> insert(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Validated @RequestBody BoardDTO.ReqBasic reqBasic)
    {
        log.info("게시글 등록");
        return boardService.insert(customUserDetails, reqBasic);
    }
    @PutMapping("/{idx}")
    public HttpEntity<?> update(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Validated @RequestBody BoardDTO.ReqBasic reqBasic,
            @PathVariable Integer idx)
    {
        log.info("게시글 수정");
        return boardService.update(customUserDetails, reqBasic, idx);
    }
    @DeleteMapping("/{idx}")
    public HttpEntity<?> delete(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Integer idx)
    {
        log.warn("게시글 삭제");
        return boardService.delete(customUserDetails, idx);
    }
    @GetMapping
    public HttpEntity<?> loadComment(
            Integer boardIdx)
    {
        log.info("댓글 불러옴");
        return boardService.findComment(boardIdx);
    }
    @PostMapping
    public HttpEntity<?> insertCommet(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Validated @RequestBody BoardCommentDTO.ReqBasic reqBasic)
    {
        log.info("댓글 달기");
        return boardService.insertComment(customUserDetails, reqBasic);
    }
    @DeleteMapping("/{cmdIdx}")
    public HttpEntity<?> deleteComment(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Integer cmdIdx)
    {
        log.info("댓글 삭제");
        return boardService.deleteComment(customUserDetails, cmdIdx);
    }
}
