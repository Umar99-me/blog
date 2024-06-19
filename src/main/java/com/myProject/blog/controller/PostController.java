package com.myProject.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.blog.payLoad.PostDto;
import com.myProject.blog.payLoad.PostResponse;
import com.myProject.blog.service.PostService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse allPosts(
        @RequestParam(value ="pageNo",defaultValue = "0",required = false) int pageNo,
        @RequestParam(value ="pageSize",defaultValue = "10",required = false) int pageSize

    ) {
        return postService.getAllPosts(pageNo,pageSize);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> postById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
    
}
