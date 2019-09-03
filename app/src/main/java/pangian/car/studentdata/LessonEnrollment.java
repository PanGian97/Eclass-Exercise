package pangian.car.studentdata;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

public class LessonEnrollment {



    @ColumnInfo(name = "lesson_id")
    private int lessonId;
    @ColumnInfo(name = "lesson_title")
    private String lessonTitle;
    @ColumnInfo(name = "index_student_mark")
    private double lessonMark;

    public LessonEnrollment() {

    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public double getLessonMark() {
        return lessonMark;
    }

    public void setLessonMark(double lessonMark) {
        this.lessonMark = lessonMark;
    }

}
