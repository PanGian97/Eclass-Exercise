package pangian.car.studentdata.Student;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pangian.car.studentdata.TaskHandler;

public class StudentViewModel extends AndroidViewModel {

    StudentRepository studentRepository;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);

    }



    public LiveData<List<Student>> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public LiveData<String> messageHandler() {
        return studentRepository.messageHandler();
    }

    public void addStudent(int am, String name, String surname) {
        studentRepository.checkIfStudentExists(new Student(am, name, surname));


    }
    public LiveData<TaskHandler> taskHandler(){
        return studentRepository.taskHandler();
    }


}
