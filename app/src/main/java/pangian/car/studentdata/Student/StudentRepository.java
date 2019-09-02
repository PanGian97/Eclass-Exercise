package pangian.car.studentdata.Student;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.LocalDatabase;
import pangian.car.studentdata.TaskHandler;

class StudentRepository {


    private StudentDao studentDao;
    MutableLiveData<String> messageToBeShown = new MutableLiveData<>();
    MutableLiveData<TaskHandler> tasksToBeDone = new MutableLiveData<>();


    public StudentRepository(Application application) {
        LocalDatabase database = LocalDatabase.getInstance(application);
        studentDao = database.studentDao();
    }

    public void insertStudent(Student student) {
        studentDao.insertStudent(student).subscribeOn(Schedulers.io())
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

public void insertLessonForStudent(int studentAm,int lessonId){
        studentDao.insertLessonForStudent(studentAm,lessonId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                         messageToBeShown.setValue("Lesson with id "+lessonId +"added to this student with AM "+studentAm);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
}





    public void checkIfStudentExists(Student student) {
      studentDao.isStudentValid(student.id).subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new SingleObserver<Integer>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onSuccess(Integer studentInstances) {
                      if (studentInstances == 0) {
                          insertStudent(student);
                      } else {
                          messageToBeShown.setValue("Student with AM = " + student.id + " already exists");
                      }
                  }

                  @Override
                  public void onError(Throwable e) {
                      messageToBeShown.setValue("Error occured with name: " + e);
                  }
              });
    }

    public LiveData<TaskHandler> taskHandler() {
        return tasksToBeDone;
    }

   LiveData<String> messageHandler(){
        return messageToBeShown;
    }


    public LiveData<List<Student>> getAllStudents() {
        return studentDao.getAllStudents();
    }
    public LiveData<Student> getStudent(int studentAm) { return  studentDao.getStudent(studentAm);}
    public LiveData<List<Lesson>> getAllStudentLessons(int studentAm){return studentDao.getAllStudentLessons(studentAm);}
}
