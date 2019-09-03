package pangian.car.studentdata.Student;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.LessonEnrollment;
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

    public LiveData<List<LessonEnrollment>> getAllStudentLessons(int studentId){return studentRepository.getAllStudentLessons(studentId);}

    public LiveData<Student> getStudent(int studentAm) { return studentRepository.getStudent(studentAm);}

    public LiveData<String> messageHandler() {
        return studentRepository.messageHandler();
    }

    public void addStudent(int am, String name, String surname) {
        studentRepository.checkIfStudentExists(new Student(am, name, surname)); }

        public void addStudentLesson(int studentAm, int lessonId){
        studentRepository.checkIfStudentIsEnrolledToLesson(studentAm,lessonId);}

    public LiveData<TaskHandler> taskHandler(){
        return studentRepository.taskHandler();
    }


    public void addMarkForLesson(int studentAm, int lessonId,double mark) {
        studentRepository.insertMarkToLessonForStudent(studentAm,lessonId,mark);
    }
}
