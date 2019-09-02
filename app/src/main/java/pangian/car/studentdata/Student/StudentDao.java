package pangian.car.studentdata.Student;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import pangian.car.studentdata.Lesson.Lesson;

@Dao
public interface StudentDao {

    @Insert()
    Completable insertStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM student_table ORDER BY student_id ASC")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT COUNT(*) FROM student_table WHERE student_id =:studentId")
    Single<Integer> isStudentValid(int studentId);

    @Query("SELECT * FROM student_table WHERE student_id =:studentId")
    LiveData<Student> getStudent(int studentId);

    @Query("SELECT lesson_id,lesson_title FROM lesson_table l " +
            "INNER JOIN student_lessons sl ON sl.index_lesson_Id = l.lesson_id " +
            "INNER JOIN student_table s ON s.student_id = sl.index_student_id " +
            "WHERE s.student_id =:studentId "
            )
    LiveData<List<Lesson>> getAllStudentLessons(int studentId);



    @Query("INSERT INTO student_lessons VALUES (null,:studentId,:lessonId)")
    Completable insertLessonForStudent(int studentId,int lessonId);

}







//select * from students s
//inner join studentLessons sl on sl.student_am = s.id
//inner join lessons l on l.id = sl.lesson_id
//where s.id = "toamtou"