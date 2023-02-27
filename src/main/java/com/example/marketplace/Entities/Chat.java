package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChat;
    private String content;
    private Date sentAt;
    private Date readAt;

    @OneToMany
    @JsonIgnore
    private Set<User> recievers;
    @ManyToOne
    @JsonIgnore
    private User sender;
    @ManyToOne
    @JsonIgnore
    private User receiver;



}
