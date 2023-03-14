package com.example.marketplace.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentCreateDTO {
    private Long userId;
    @Lob
    private String content;
    private Instant createdDate;
}
