package com.example.movieProject.module.board.controller;

import com.example.movieProject.config.security.CustomUserDetails;
import com.example.movieProject.module.board.dto.AgoraDTO;
import com.example.movieProject.module.board.dto.AgoraDiscussDTO;
import com.example.movieProject.module.board.service.AgoraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agora")
public class AgoraController {
    private final AgoraService agoraService;
    @GetMapping
    public HttpEntity<?> searchAgora(String keyField, String keyWord)
    {
        log.info("목록 불러옴");
        return agoraService.findAgora(keyField, keyWord);
    }
    @GetMapping
    public HttpEntity<?> getAgora(Integer idx)
    {
        log.info("토론글 불러옴");
        return agoraService.getAgora(idx);
    }
    @PostMapping
    public HttpEntity<?> insertAgora(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Validated @RequestBody AgoraDTO.ReqBasic reqBasic)
    {
        log.info("토론글 등록");
        return agoraService.createAgora(customUserDetails, reqBasic);
    }
    @DeleteMapping("/{agoraIdx}")
    public HttpEntity<?> deleteAgora(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Integer agoraIdx)
    {
        log.warn("토론글 삭제");
        return agoraService.deleteAgora(customUserDetails, agoraIdx);
    }
    @GetMapping
    public HttpEntity<?> getDiscuss(Integer idx)
    {
        log.info("댓글 불러옴");
        return agoraService.getDiscuss(idx);
    }
    @PostMapping
    public HttpEntity<?> insertDiscuss(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Validated @RequestBody AgoraDiscussDTO.ReqBasic reqBasic)
    {
        log.info("댓글 등록");
        return agoraService.insertDiscuss(customUserDetails, reqBasic);
    }
    @DeleteMapping("/{discussIdx}")
    public HttpEntity<?> deleteDiscuss(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Integer discussIdx)
    {
        log.warn("댓글 삭제");
        return agoraService.deleteDiscuss(customUserDetails, discussIdx);
    }
}
