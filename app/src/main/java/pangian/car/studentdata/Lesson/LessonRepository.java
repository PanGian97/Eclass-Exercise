package pangian.car.studentdata.Lesson;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

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


    public void insertLesson(Lesson lesson) {
        new InsertLessonAsyncTask(lessonDao).execute(lesson);
    }

    private static class InsertLessonAsyncTask extends AsyncTask<Lesson, Void, Void> {
        private LessonDao lessonDao;

        private InsertLessonAsyncTask(LessonDao lessonDao) {
            this.lessonDao = lessonDao;
        }

        @Override
        protected Void doInBackground(Lesson...lessons) {
            lessonDao.insertLesson(lessons[0]);
            return null;
        }
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
        if(duplicates==0){
            insertLesson(lessonForVerification.getValue());
        }
        else{
            messageToBeShown.setValue("Lesson with this ID already exists");
        }
    }

    LiveData<String> messageHandler(){
        return messageToBeShown;
    }
    LiveData<List<Lesson>> getAllLessons() {
        return lessonDao.getAllLessons();
    }
}
