package com.madhav.quizservice.Service;

import com.madhav.quizservice.Dao.QuizDao;
import com.madhav.quizservice.Feign.QuizInterface;
import com.madhav.quizservice.Model.Question;
import com.madhav.quizservice.Model.QuestionWrapper;
import com.madhav.quizservice.Model.Quiz;
import com.madhav.quizservice.Model.Response;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionIds = quiz.get().getQuestionIds();
        List<QuestionWrapper> questionWrapperList = quizInterface.getQuestionsFromId(questionIds).getBody();
        return new ResponseEntity<>(questionWrapperList,HttpStatus.OK);
    }
    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
