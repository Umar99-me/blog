package com.myProject.blog.payLoad;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDetails {

    private Date timeStamp;
    private String message;
    private String details;

}
