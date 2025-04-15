package com.divyanshu.bloggingapi.payloads;

import com.divyanshu.bloggingapi.entities.Comment;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotBlank
    @Size(min = 3,message = "Username must contain more than 3 characters")
    private String name;
    @Email(message = "Please enter a valid email.")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 5,max = 10,message = "Password must contain 5-10 characters")
    private String password;
    @NotNull
    private String about;
    private List<CommentDto> comments;
}
