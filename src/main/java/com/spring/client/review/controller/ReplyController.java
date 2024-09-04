package com.spring.client.review.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.spring.client.review.domain.Reply;
import com.spring.client.review.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/replyInsert", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reply insertReply(@RequestBody Reply reply) {
        return replyService.saveReply(reply);
    }

    @GetMapping(value = "/replies/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reply> getReplies(@PathVariable Long reviewId) {
        return replyService.findRepliesByReviewId(reviewId);
    }

    @PutMapping(value = "/reply/{replyId}", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reply updateReply(@PathVariable Long replyId, @RequestBody Reply reply) {
        return replyService.updateReply(replyId, reply.getReplyContent());
    }

    @DeleteMapping(value = "/reply/{replyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
    }
}
