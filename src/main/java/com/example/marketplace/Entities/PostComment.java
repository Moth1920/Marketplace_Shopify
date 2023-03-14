package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postCommentId;


    @Lob
    private String content;

    private Instant createdDate = Instant.now();

    @OneToMany(
            cascade = CascadeType.ALL,
            // orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "commentId")
    private List<PostCommentLike> postCommentLikes = new ArrayList<>();

    private int likesNumber;




}

