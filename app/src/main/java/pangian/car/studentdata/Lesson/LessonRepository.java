package pangian.car.studentdata.Lesson;

import android.app.Application;
import android.os.AsyncTask;

import androidx.core.util.Consumer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pangian.car.studentdata.LocalDatabase;
import pangian.car.studentdata.Student.Student;
import pangian.car.studentdata.TaskHandler;

class LessonRepository {


    private LessonDao lessonDao;
    MutableLiveData<String> messageToBeShown = new MutableLiveData<>();
    MutableLiveData<TaskHandler> tasksToBeDone = new MutableLiveData<>();

    public LessonRepository(Application application) {
        LocalDatabase database = LocalDatabase.getInstance(application);
        lessonDao = database.lessonDao();

    }


    public void insertLesson(Lesson lesson) {
        lessonDao.insertLesson(lesson).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        tasksToBeDone.setValue(TaskHandler.REDIRECT);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }



    public void checkIfLessonExists(Lesson lesson) {

        lessonDao.isLessonValid(lesson.id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer lessonInstances) {
                        if (lessonInstances == 0) {
                            insertLesson(lesson);
                        } else {
                            messageToBeShown.setValue("Lesson with ID = " + lesson.id + " already exists");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        messageToBeShown.setValue("Error occured with name: " + e);
                    }
                });
    }

    LiveData<String> messageHandler() {
        return messageToBeShown;
    }

    LiveData<List<Lesson>> getAllLessons() {
        return lessonDao.getAllLessons();
    }

    LiveData<TaskHandler> taskHandler() {
        return tasksToBeDone;
    }


}



