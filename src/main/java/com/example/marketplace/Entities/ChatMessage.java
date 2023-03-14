package com.example.marketplace.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ChatMessage {
    @Getter
    private MessageType type;
    @Getter
    private String content;
    @Getter
    private String sender;
    @Getter
    private String time;
}
