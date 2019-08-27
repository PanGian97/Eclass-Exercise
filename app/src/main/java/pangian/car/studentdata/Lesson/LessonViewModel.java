package pangian.car.studentdata.Lesson;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

class LessonViewModel extends AndroidViewModel {
    LessonRepository lessonRepository;
    public LessonViewModel(@NonNull Application application) {
        super(application);

       lessonRepository = new LessonRepository(application);
    }

    public void addLesson(int id,String name) {
        lessonRepository.checkIfLessonExists(new Lesson(id,name));
    }
}
