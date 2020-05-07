package com.netrox.demo.repository;


import com.netrox.demo.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepo extends CrudRepository<Question,Long> {
List<Question> findAllByDeletedIsFalse ();
//@Where(clause = "deleted = false")
Question findByIdAndDeletedIsFalse (Long id );

@Query (value = "SELECT * FROM question_model a inner join answer_model b on a.id=QUESTION_MODEL_ID and b.QUESTION_MODEL_ID=1 and b.answer like '%fa';",nativeQuery=true)
Question findByKurac ();



//List<Question> findQuestionByAnswerCorrect(boolean bool);

    //QuestionModel findByIdAndActiveAnswers (Long id);
    //@Query("select  q from QuestionModel q where q.answers")s
    /*@Query("SELECT * FROM question q WHERE q.id = :id JOIN q.answers a WHERE a.deleted = :status\")\n" +
            "Question findByIdWithAnswers(@Param(\"id\") Long id, @Param(\"status\") Boolean status")*/

    //@Query(value="SELECT * FROM question_model a join answer_model b on a.id=b.FK_Question_id and b.FK_Question_id=1 and b.correct=false;",nativeQuery = true)
    //@Query(value = "SELECT a.id,a.question,a.level,a.deleted, FROM question_model a where  a.id=1;",nativeQuery = true)

    /*@Query("SELECT a.id,a.question,a.level,a.deleted, "
            + "new com.netrox.demo.model.AnswerModel(b.id, b.answer,b.correct,b.deleted) as answer"
           +" FROM QuestionModel  a join AnswerModel b on a.id=b.id and b.id=1;")*/
    //@Query(value = "SELECT a.id,a.question,a.level,a.deleted,b.correct  FROM question_model a join answer_model b on a.id=b.id where a.id=1;",nativeQuery = true)
  /*  @Query("select distinct new com.netrox.demo.model.Test(e.id,e.question,e.deleted,f.id,f.answer,f.correct,f.deleted) " +
            "from QuestionModel e INNER JOIN FETCH AnswerModel f on e.id=f.questionModel.id")
    Test findTestWithIdOne();
*/
    // QuestionModel findByIdWithAnswers(@Param("id") Long id);



}


//WARNING 99% treba stavit optional da vracaju kao sto to radi i findbyid i ti ugradeni.