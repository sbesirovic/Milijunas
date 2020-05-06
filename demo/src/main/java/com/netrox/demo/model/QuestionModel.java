package com.netrox.demo.model;



import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "demomodel")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private int level;
    private boolean deleted;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String user;
    //like je odg
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "demomodel_id")
    private Set<AnswerModel> answers = new HashSet<>();

    public Set<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerModel> answers) {
        this.answers = answers;
    }

    public QuestionModel() {
        user = "Sefer Besirovic";
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isDeleted() { return deleted; }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
