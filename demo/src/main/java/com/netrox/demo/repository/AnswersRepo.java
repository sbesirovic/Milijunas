package com.netrox.demo.repository;

import com.netrox.demo.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersRepo extends JpaRepository<Answer,Long> {
List<Answer> findAllByDeletedIsFalse();
    //List<AnswerModel> findAnswerModelsByDeletedIsFalse();
    Answer findByIdAndDeletedIsFalse(Long id);
    //Answer findByCorrect(boolean bool);
    //List<Answer> findAllByDeletedIsFalseAndQuestion_IdAndQuestionAndDeletedIsFalse(Long id);

}
