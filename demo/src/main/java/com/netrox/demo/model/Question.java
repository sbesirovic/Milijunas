package com.netrox.demo.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.Nullable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "question_model")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"deleted","createdAt","createdBy","updatedAt","updatedBy"}, allowGetters = false) // ako stavis true onda ovi sto imaju geteri ce se vracat u json 50% ako ti treba deleted u kodu da ne mogne ?
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private String question;

    private int level;
    private boolean deleted;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;
    //like je odg
    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER, cascade = CascadeType.ALL,targetEntity = Answer.class)
    //@JoinColumn(name = "FK_Question_id") // mapped by vs joincolumn
    //@Where(clause = "deleted = false") jedan od nacina da se obrisani odgovori ne pojavljuju u pitanje
    //@Autowired
    private List<Answer> answer;


    public void addAnswer(Answer an)
    {
        if(answer == null)
        {
            answer = new ArrayList<Answer>();
        }
        answer.add(an);
    }

    /*

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "deleted = false")
    @Autowired
    private List<AnswerModel> activeAnswers;*/

    public List<Answer>  getAnswers() {
        return answer;
    }

    public void setAnswers(List<Answer> answers) {
        this.answer = answers;
    }

    public Question() {
        createdBy = "Sefer Besirovic";
    }

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
