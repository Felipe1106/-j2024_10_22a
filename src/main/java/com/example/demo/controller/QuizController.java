package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@SessionAttributes({"score", "questionIndex"})
public class QuizController {

    private List<Question> questions = List.of(
            new Question("What is the capital of France?", "Paris"),
            new Question("What is 2 + 2?", "4"),
            new Question("What is the largest planet?", "Jupiter")
    );

    @GetMapping("/quiz")
    public String startQuiz(Model model) {
        model.addAttribute("score", 0);
        model.addAttribute("questionIndex", 0);
        model.addAttribute("question", questions.get(0));
        return "quiz";
    }

    @PostMapping("/submitAnswer")
    public String submitAnswer(@RequestParam("answer") String answer,
                               Model model,
                               @RequestParam("questionIndex") int questionIndex,
                               @RequestParam("score") int score) {

        Question currentQuestion = questions.get(questionIndex);

        if (currentQuestion.getAnswer().equalsIgnoreCase(answer.trim())) {
            score++; // Correct answer increases score
        }

        questionIndex++; // Move to the next question
        model.addAttribute("score", score);
        model.addAttribute("questionIndex", questionIndex);

        if (questionIndex < questions.size()) {
            model.addAttribute("question", questions.get(questionIndex));
            return "quiz"; // Return to the same view with the updated data
        } else {
            model.addAttribute("finalScore", score);
            return "redirect:/quizResult"; // Redirect to the results page when the quiz is over
        }
    }

    @GetMapping("/quizResult")
    public String quizResult(Model model, SessionStatus sessionStatus) {
        // Retrieve score from session
        Integer finalScore = (Integer) model.getAttribute("score");
        if (finalScore == null) {
            return "error"; // If no score exists, something went wrong, show error page
        }

        // Complete the session (reset session attributes)
        sessionStatus.setComplete();
        
        // Set final score in the model for display on quizResult page
        model.addAttribute("finalScore", finalScore);
        model.addAttribute("questionsSize", questions.size());

        
        return "quizResult";
    }

}

// Simple Question class for holding question and answer
class Question {
    private String question;
    private String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}