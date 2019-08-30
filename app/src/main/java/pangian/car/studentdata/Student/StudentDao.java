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

@Dao
public interface StudentDao {

    @Insert()
    Completable insertStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM student_table ORDER BY am ASC")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT COUNT(*) FROM student_table WHERE am =:studentAm")
    Single<Integer> isStudentValid(int studentAm);
}
