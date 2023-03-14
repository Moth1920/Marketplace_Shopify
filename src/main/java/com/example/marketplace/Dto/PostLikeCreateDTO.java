package com.example.marketplace.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeCreateDTO {
    private Long userId;
    private boolean etat;
}
