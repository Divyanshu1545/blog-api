package com.divyanshu.bloggingapi.services;

import com.divyanshu.bloggingapi.entities.Category;
import com.divyanshu.bloggingapi.entities.Post;
import com.divyanshu.bloggingapi.payloads.PostDto;
import com.divyanshu.bloggingapi.payloads.PostResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer id);

    PostDto getPostById(Integer id);

    void deletePost(Integer id);

    PostResponse getAllPosts(Integer pageSize, Integer pageNumber, String sortBy);

    PostResponse getAllPostsByCategory(Integer categoryId, Integer pageSize, Integer pageNumber, String sortBy);

    PostResponse getAllPostsByUser(Integer userId, Integer pageSize, Integer pageNumber,String sortBy);

    PostResponse searchPosts(String keyword, Integer pageSize, Integer pageNumber);
}
