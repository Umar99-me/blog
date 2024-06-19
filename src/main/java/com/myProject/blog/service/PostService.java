package com.myProject.blog.service;

import com.myProject.blog.payLoad.PostDto;
import com.myProject.blog.payLoad.PostResponse;

public interface PostService {

    PostDto createPost( PostDto postDto);
    PostResponse getAllPosts(int pageNo,int pageSize);
    PostDto getPostById(long id);
}
