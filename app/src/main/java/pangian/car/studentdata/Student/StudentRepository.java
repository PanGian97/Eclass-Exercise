package pangian.car.studentdata.Student;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.LessonEnrollment;
import pangian.car.studentdata.LocalDatabase;
import pangian.car.studentdata.TaskHandler;

class StudentRepository {


    private StudentDao studentDao;
    private MutableLiveData<String> messageToBeShown = new MutableLiveData<>();
    private MutableLiveData<TaskHandler> tasksToBeDone = new MutableLiveData<>();


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
                        messageToBeShown.setValue("An error occured inserting lesson to student ");
                    }
                });
}

    public void insertMarkToLessonForStudent(int studentAm, int lessonId,double mark) {
      studentDao.insertMarkToLessonForStudent(studentAm,lessonId,mark).subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new CompletableObserver() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onComplete() {
                    messageToBeShown.setValue("Mark with value"+mark+"has been setted to Student with AM= "+studentAm);
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
    public LiveData<List<LessonEnrollment>> getAllStudentLessons(int studentAm){return studentDao.getAllStudentLessons(studentAm);}


    public void checkIfStudentIsEnrolledToLesson(int studentAm, int lessonId) {
        studentDao.isStudentEnrolled(studentAm,lessonId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer lessonInstances) {
                        if (lessonInstances == 0) {
                            insertLessonForStudent(studentAm,lessonId);
                        } else {
                            messageToBeShown.setValue("Student is already enrolled on this Lesson");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
