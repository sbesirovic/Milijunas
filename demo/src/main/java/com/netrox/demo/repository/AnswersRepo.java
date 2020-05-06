package com.netrox.demo.repository;

import com.netrox.demo.model.AnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersRepo extends JpaRepository<AnswerModel,Long> {
List<AnswerModel> findAllByDeleted(boolean delete);
AnswerModel findByIdAndDeleted(Long id, boolean delete);
}
