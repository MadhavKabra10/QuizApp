package com.madhav.questionservice.Service;

import com.madhav.questionservice.Dao.QuestionDao;
import com.madhav.questionservice.Model.Question;
import com.madhav.questionservice.Model.QuestionWrapper;
import com.madhav.questionservice.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao dao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        return new ResponseEntity<>(dao.findQuestionsByCategory(category),HttpStatus.OK);
    }
    public ResponseEntity<String> addQuestion(Question question){
        dao.save(question);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    //we will only return the id of each questions
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQ) {
        List<Integer> questionIdList = dao.findRandomQuestionsByCategory(categoryName,numQ);
        return new ResponseEntity<>(questionIdList,HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<Question> questionList = new ArrayList<>();
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();
        for(Integer id : questionIds){
            Optional<Question> question = dao.findById(id);
            question.ifPresent(questionList::add);
        }
        for(Question it : questionList){
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setQuestionTitle(it.getQuestionTitle());
            questionWrapper.setId(it.getId());
            questionWrapper.setOption1(it.getOption1());
            questionWrapper.setOption2(it.getOption2());
            questionWrapper.setOption3(it.getOption3());
            questionWrapper.setOption4(it.getOption4());
            questionWrapperList.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrapperList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responseList) {
        int rightAnswers = 0;
        for(Response response : responseList){
            int id = response.getId();
            Optional<Question> question = dao.findById(id);
            if(question.get().getRightAnswer().equals(response.getResponse())){
                rightAnswers++;
            }
        }
        return new ResponseEntity<>(rightAnswers,HttpStatus.OK);
    }
}
