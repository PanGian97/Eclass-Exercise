package pangian.car.studentdata.Lesson;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface LessonDao {


    @Insert()
    Completable insertLesson(Lesson lesson);

    @Delete
    void deleteLesson(Lesson lesson);

    @Query("SELECT * FROM lesson_table ORDER BY id ASC")
    LiveData<List<Lesson>> getAllLessons();

    @Query("SELECT COUNT(*) FROM lesson_table WHERE id =:lessonId")
    Single<Integer> isLessonValid(int lessonId);
}
