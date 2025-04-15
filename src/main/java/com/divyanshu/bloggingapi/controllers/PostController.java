package com.divyanshu.bloggingapi.controllers;

import com.divyanshu.bloggingapi.config.AppConstants;
import com.divyanshu.bloggingapi.payloads.ApiResponse;
import com.divyanshu.bloggingapi.payloads.PostDto;
import com.divyanshu.bloggingapi.payloads.PostResponse;
import com.divyanshu.bloggingapi.services.FileService;
import com.divyanshu.bloggingapi.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;
    @PostMapping(path = "/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId){
    PostDto createdPost = postService.createPost(postDto,userId,categoryId);
    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping(path = "/posts/{postId}")
    ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    //get by user
    @GetMapping(path = "/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy){
        PostResponse posts = postService.getAllPostsByUser(userId, pageSize,pageNumber, sortBy);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    //get all posts by user
    @GetMapping(path = "/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(
            @PathVariable("categoryId") Integer categoryId,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy){
        PostResponse posts = postService.getAllPostsByCategory(categoryId,pageSize,pageNumber,sortBy);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping(path = "/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy) {
        PostResponse posts = postService.getAllPosts(pageSize,pageNumber,sortBy);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping(path = "/posts/search/{keyword}")
    public ResponseEntity<PostResponse> getAllPostsContaining(
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @PathVariable String keyword){
        PostResponse posts = postService.searchPosts(keyword, pageSize, pageNumber);
        return new ResponseEntity<>(posts,HttpStatus.OK);


    }


    @DeleteMapping(path = "/posts/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("id") Integer id){
        postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Post deleted Successfully.", true),HttpStatus.OK);
    }

    @PutMapping(path = "/posts/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable("id") Integer id,
            @RequestBody PostDto postDto){
        PostDto updatedPost = postService.updatePost(postDto,id);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @PostMapping(path = "/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(
            @RequestParam MultipartFile image,
            @PathVariable Integer postId) throws IOException {
        PostDto postDto = postService.getPostById(postId);
        String fileName = fileService.uploadImage(path, image);
        postDto.setImageUrl(fileName);
        PostDto updatedPost= postService.updatePost(postDto,postId);
        return  new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

}
