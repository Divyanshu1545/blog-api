package com.divyanshu.bloggingapi.payloads;

import com.divyanshu.bloggingapi.entities.Category;
import com.divyanshu.bloggingapi.entities.Comment;
import com.divyanshu.bloggingapi.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer id;
    private String title;
    private String content;
    private String imageUrl;
    private Date dateCreated;
    private CategoryDto category;
    private UserDto user;
    private List<CommentDto> comments;
}
