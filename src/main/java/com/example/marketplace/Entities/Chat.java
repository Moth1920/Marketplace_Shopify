package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChat;
    private String content;
    private Date sentAt;
    private Date readAt;

    @OneToMany(mappedBy = "chat")
    @JsonIgnore
    private Set<User> recievers;
    @ManyToOne
    @JsonIgnore
    private User sender;
    @ManyToOne
    @JsonIgnore
    private User receiver;



}
