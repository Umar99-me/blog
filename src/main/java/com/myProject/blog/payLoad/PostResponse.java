package com.myProject.blog.payLoad;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pagesize;
    private int totalNoOfPages;
    private boolean last;
    private long totalElements;
}
