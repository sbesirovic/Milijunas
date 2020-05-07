package com.netrox.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "answer_model")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"deleted","createdAt","createdBy","updatedAt","updatedBy"}, allowGetters = false)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;
    private boolean correct;
    protected boolean deleted;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @JsonIgnore
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name ="questionModel_id")
    private Question question;


    // constructor
    public Answer() {
        createdBy = "Sefer Besirovic";
    }

    /*public AnswerModel(Long id,String answer, boolean correct,boolean deleted) {

        createdBy = "Sefer Besirovic";
        createdAt = null;
        this.id=id;
        this.answer=answer;
        this.correct=correct;
        this.deleted=deleted;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isDeleted() { return deleted; }

    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
