package org.temirjohn.temirjhon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.temirjohn.temirjhon.request.QuestionRequest;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Questions;
import org.temirjohn.temirjhon.repository.CourseRepository;
import org.temirjohn.temirjhon.repository.QuestionRepository;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository, CourseRepository courseRepository) {
        this.questionRepository = questionRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody QuestionRequest questionRequest) {
        Course course = courseRepository.findById(questionRequest.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Questions question = new Questions();
        question.setQuestion(questionRequest.getQuestion());
        question.setOption1(questionRequest.getOption1());
        question.setOption2(questionRequest.getOption2());
        question.setOption3(questionRequest.getOption3());
        question.setOption4(questionRequest.getOption4());
        question.setAnswer(questionRequest.getAnswer());
        question.setCourse(course);

        questionRepository.save(question);

        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Questions>> getQuestionsByCourseId(@RequestParam Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        List<Questions> questions = questionRepository.findByCourse(course);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questions> getQuestionById(@PathVariable Long id) {
        Optional<Questions> question = questionRepository.findById(id);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Questions>> getQuestionsByCoursePath(@PathVariable Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        List<Questions> questions = questionRepository.findByCourse(course);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Questions> updateQuestion(@PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        Questions question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        Course course = courseRepository.findById(questionRequest.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        question.setQuestion(questionRequest.getQuestion());
        question.setOption1(questionRequest.getOption1());
        question.setOption2(questionRequest.getOption2());
        question.setOption3(questionRequest.getOption3());
        question.setOption4(questionRequest.getOption4());
        question.setAnswer(questionRequest.getAnswer());
        question.setCourse(course);

        questionRepository.save(question);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        Questions question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        questionRepository.delete(question);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }
}