package pangian.car.studentdata.Lesson;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import pangian.car.studentdata.LocalDatabase;
import pangian.car.studentdata.Student.Student;

class LessonRepository implements OnLessonValidationResult{

    private LessonDao lessonDao;
    MutableLiveData<Lesson> lessonForVerification = new MutableLiveData<>();
    MutableLiveData<String> messageToBeShown = new MutableLiveData<>();

    public LessonRepository(Application application) {
        LocalDatabase database = LocalDatabase.getInstance(application);
        lessonDao = database.lessonDao();

    }

    public void checkIfLessonExists(Lesson lesson) {

           lessonForVerification.setValue(lesson);
            new validateLesson(lessonDao,this).execute(lesson);

    }

    private static class validateLesson extends AsyncTask<Lesson, Void, Integer> {
    private LessonDao lessonDao;

        public validateLesson(LessonDao lessonDao, OnLessonValidationResult listener) {
            this.lessonDao = lessonDao;
            this.listener = listener;
        }

        private OnLessonValidationResult listener;

        @Override
        protected Integer doInBackground(Lesson... lessons) {
            return lessonDao.isLessonValid(lessons[0].id);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            listener.validateLesson(integer);
        }
    }


    @Override
    public void validateLesson(int duplicates) {

    }

}
