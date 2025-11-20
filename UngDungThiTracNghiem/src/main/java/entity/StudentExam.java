package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "student_exams")
public class StudentExam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(nullable = false)
    private int score;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public StudentExam() {}

    public StudentExam(User student, Exam exam, int score, Date startTime, Date endTime) {
        this.student = student;
        this.exam = exam;
        this.score = score;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentExam that = (StudentExam) o;
        return score == that.score && Objects.equals(id, that.id) && Objects.equals(student, that.student) && Objects.equals(exam, that.exam) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, exam, score, startTime, endTime);
    }

    @Override
    public String toString() {
        return "StudentExam{" +
                "id=" + id +
                ", student=" + student +
                ", exam=" + exam +
                ", score=" + score +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
