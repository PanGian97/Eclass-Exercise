package pangian.car.studentdata.Student;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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




    public LiveData<List<Student>> getAllStudents() {
        return studentDao.getAllStudents();
    }



    public void checkIfStudentExists(Student student) {
      studentDao.isStudentValid(student.am).subscribeOn(Schedulers.io())
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
                          messageToBeShown.setValue("Student with AM = " + student.am + " already exists");
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

}
