package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "exam_questions")
public class ExamQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    public ExamQuestion() {}

    public ExamQuestion(Exam exam, Question question) {
        this.exam = exam;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ExamQuestion that = (ExamQuestion) o;
        return Objects.equals(id, that.id) && Objects.equals(exam, that.exam) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exam, question);
    }

    @Override
    public String toString() {
        return "ExamQuestion{" +
                "id=" + id +
                ", exam=" + exam +
                ", question=" + question +
                '}';
    }
}
