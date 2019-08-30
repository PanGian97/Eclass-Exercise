package pangian.car.studentdata.Lesson;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LessonDao {


    @Insert()
    void insertLesson(Lesson lesson);

    @Delete
    void deleteLesson(Lesson lesson);

    @Query("SELECT * FROM lesson_table ORDER BY id ASC")
    LiveData<List<Lesson>> getAllLessons();

    @Query("SELECT COUNT(*) FROM lesson_table WHERE id =:lessonId")
    int isLessonValid( int lessonId);
}
