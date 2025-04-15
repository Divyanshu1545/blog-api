package com.divyanshu.bloggingapi.services.impl;

import com.divyanshu.bloggingapi.entities.Comment;
import com.divyanshu.bloggingapi.entities.Post;
import com.divyanshu.bloggingapi.exceptions.ResourceNotFoundException;
import com.divyanshu.bloggingapi.payloads.CommentDto;
import com.divyanshu.bloggingapi.payloads.PostDto;
import com.divyanshu.bloggingapi.repositories.CommentRepository;
import com.divyanshu.bloggingapi.repositories.PostRepository;
import com.divyanshu.bloggingapi.services.CommentService;
import com.divyanshu.bloggingapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId) {
        Post post =postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        comment.setUser(post.getUser());
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment", "Id", id));
        commentRepository.delete(comment);
    }
}
