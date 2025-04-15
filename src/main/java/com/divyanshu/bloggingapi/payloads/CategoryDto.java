package com.divyanshu.bloggingapi.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer id;
    @NotNull
    @NotBlank(message = "Title Cannot be left empty.")
    @Size(min = 4)
    private String categoryTitle;
    @NotBlank(message = "Cannot leave description empty.")
    @Size(min = 10,message = "Description must contain at least 10 characters.")
    private String description;
}
