package com.netrox.demo.repository;


import com.netrox.demo.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepo extends JpaRepository<QuestionModel,Long> {
List<QuestionModel> findAllByDeleted (boolean delete);
    QuestionModel findByIdAndDeleted (Long id,boolean delete );
}
