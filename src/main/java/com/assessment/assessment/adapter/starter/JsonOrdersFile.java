package com.assessment.assessment.adapter.starter;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class JsonOrdersFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    private String content;

    public JsonOrdersFile() {}

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}