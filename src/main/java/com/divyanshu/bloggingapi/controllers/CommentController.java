package com.divyanshu.bloggingapi.controllers;

import com.divyanshu.bloggingapi.payloads.ApiResponse;
import com.divyanshu.bloggingapi.payloads.CommentDto;
import com.divyanshu.bloggingapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping(path = "/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto createdCommentDto = commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(commentDto,HttpStatus.CREATED);
    }
    @DeleteMapping(path = "comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully.",true), HttpStatus.OK);
    }
}
