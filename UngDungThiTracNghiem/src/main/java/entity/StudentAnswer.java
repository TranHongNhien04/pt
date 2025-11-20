package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "student_answers")
public class StudentAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_exam_id")
    private StudentExam studentExam;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private String chosenAnswer;

    public StudentAnswer() {}

    public StudentAnswer(StudentExam studentExam, Question question, String chosenAnswer) {
        this.studentExam = studentExam;
        this.question = question;
        this.chosenAnswer = chosenAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentExam getStudentExam() {
        return studentExam;
    }

    public void setStudentExam(StudentExam studentExam) {
        this.studentExam = studentExam;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(String chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentAnswer that = (StudentAnswer) o;
        return Objects.equals(id, that.id) && Objects.equals(studentExam, that.studentExam) && Objects.equals(question, that.question) && Objects.equals(chosenAnswer, that.chosenAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentExam, question, chosenAnswer);
    }

    @Override
    public String toString() {
        return "StudentAnswer{" +
                "id=" + id +
                ", studentExam=" + studentExam +
                ", question=" + question +
                ", chosenAnswer='" + chosenAnswer + '\'' +
                '}';
    }
}
