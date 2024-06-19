package com.myProject.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

}
