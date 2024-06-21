package com.myProject.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.myProject.blog.entity.Post;
import com.myProject.blog.exception.ResourceNotFoundException;
import com.myProject.blog.payLoad.PostDto;
import com.myProject.blog.payLoad.PostResponse;
import com.myProject.blog.repository.PostRepository;
import com.myProject.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private ModelMapper modelMapper; 

    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post newPost = postRepository.save(mapToEntity(postDto));
        return mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> page= postRepository.findAll(pageable);
        PostResponse postResponse = new PostResponse();
        List<PostDto> postDtos=page.getContent().stream().map(post->mapToDto(post)).collect(Collectors.toList());
        postResponse.setContent(postDtos);
        postResponse.setPageNo(page.getNumber());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalNoOfPages(page.getTotalPages());
        postResponse.setPagesize(page.getSize());
        postResponse.setLast(page.isLast());
        return postResponse;
    }   

    private PostDto mapToDto(Post post){

        // PostDto postDto = new PostDto();
        // postDto.setId(post.getId());
        // postDto.setTitle(post.getTitle());
        // postDto.setContent(post.getContent());
        // postDto.setDescription(post.getDescription());  
        return modelMapper.map(post, (new PostDto()).getClass());
        
    }

    private Post mapToEntity(PostDto postDto){

        // Post post = new Post();
        // post.setTitle(postDto.getTitle());
        // post.setContent(postDto.getDescription());
        // post.setDescription(postDto.getDescription());
        return modelMapper.map(postDto, (new Post()).getClass());
    }
    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        return mapToDto(post);
    }

}
