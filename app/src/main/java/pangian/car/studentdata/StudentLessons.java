package pangian.car.studentdata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.Student.Student;


@Entity(tableName = "student_lessons", indices = {@Index(value = "index_student_id"),
        @Index(value = "index_lesson_id"), @Index(value = "index_student_mark")},
        foreignKeys = {@ForeignKey(entity = Student.class, parentColumns = "student_id", childColumns = "index_student_id"),
                @ForeignKey(entity = Lesson.class, parentColumns = "lesson_id", childColumns = "index_lesson_id")})

public class StudentLessons {

    @PrimaryKey(autoGenerate = true)
    public int primaryId;
    @ColumnInfo(name = "index_student_id")
    public int studentId;
    @ColumnInfo(name = "index_lesson_id")
    public int lessonId;
    @ColumnInfo(name = "index_student_mark")
    public double studentMark;



    public StudentLessons() {

    }

    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;

    }    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public double getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(double studentMark) {
        this.studentMark = studentMark;
    }

}

