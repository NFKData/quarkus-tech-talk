package com.everis.auto.techtalk.quarkus.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "greetings")
public class GreetingEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "content")
    private String content;

    @Column(name = "creationtimestamp")
    private Timestamp creationTimestamp;

    @PrePersist
    void prePersist() {
        this.code = UUID.randomUUID().toString();
        this.creationTimestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String number) {
        this.code = number;
    }

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
