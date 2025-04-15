package com.divyanshu.bloggingapi.entities;

import com.divyanshu.bloggingapi.payloads.CategoryDto;
import com.divyanshu.bloggingapi.payloads.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 100)
    private String title;
    @Column(nullable = false, length = 10000)
    private String content;
    private String imageUrl;
    private Date dateCreated;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Comment> comments;

}
