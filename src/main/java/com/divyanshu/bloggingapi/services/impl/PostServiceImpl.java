package com.divyanshu.bloggingapi.services.impl;

import com.divyanshu.bloggingapi.entities.Category;
import com.divyanshu.bloggingapi.entities.Post;
import com.divyanshu.bloggingapi.entities.User;
import com.divyanshu.bloggingapi.exceptions.ResourceNotFoundException;
import com.divyanshu.bloggingapi.payloads.PostDto;
import com.divyanshu.bloggingapi.payloads.PostResponse;
import com.divyanshu.bloggingapi.repositories.CategoryRepository;
import com.divyanshu.bloggingapi.repositories.PostRepository;
import com.divyanshu.bloggingapi.repositories.UserRepository;
import com.divyanshu.bloggingapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        Post post = modelMapper.map(postDto,Post.class);
        post.setImageUrl("default.png");
        post.setDateCreated(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "Id", id));
        post.setTitle(postDto.getTitle());;
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        Post updatedPost = postRepository.save(post);
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","Id", id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public void deletePost(Integer id) {
    Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","Id", id));
    postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageSize, Integer pageNumber, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Post> postPage = postRepository.findAll(pageable);
        List<PostDto> posts = postRepository.findAll(pageable).getContent().stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = modelMapper.map(postPage,PostResponse.class);
        return postResponse;
    }

    @Override
    public PostResponse getAllPostsByCategory(Integer categoryId ,Integer pageSize, Integer pageNumber, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        Page<Post> posts = postRepository.findByCategory(category, pageable);
        PostResponse postResponse = modelMapper.map(posts,PostResponse.class);
        return postResponse;
    }

    @Override
    public PostResponse getAllPostsByUser(Integer userId, Integer pageSize, Integer pageNumber, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Page<Post> posts = postRepository.findByUser(user, pageable);
        return modelMapper.map(posts,PostResponse.class);
    }

    @Override
    public PostResponse searchPosts(String keyword,Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> posts = postRepository.findByTitleContaining(keyword,pageable);
        return modelMapper.map(posts,PostResponse.class);
    }
}
