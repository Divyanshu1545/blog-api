package com.divyanshu.bloggingapi.services;

import com.divyanshu.bloggingapi.payloads.CommentDto;
import com.divyanshu.bloggingapi.payloads.PostDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer id);
}
