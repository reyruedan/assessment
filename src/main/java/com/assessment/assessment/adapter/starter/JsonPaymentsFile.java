package com.assessment.assessment.adapter.starter;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class JsonPaymentsFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    public JsonPaymentsFile() {}

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}