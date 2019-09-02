package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.R;

public class StudentDetailsActivity extends AppCompatActivity {

    TextView studentName;
    TextView studentLesson;
    StudentViewModel studentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        studentName = findViewById(R.id.student_details_name);
        studentLesson = findViewById(R.id.student_lesson_name);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

        int studentAm = intent.getIntExtra("student_am", 0);
        getStudent(studentAm);
        getLessons(studentAm);
    }


    private void getStudent(int studentAm) {
        studentViewModel.getStudent(studentAm).observe(this, new Observer<Student>() {
            @Override
            public void onChanged(Student student) {
                studentName.setText(student.name);
            }
        });
    }

    private void getLessons(int studentAm) {
        studentViewModel.getAllStudentLessons(studentAm).observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(List<Lesson> lessons) {
                studentLesson.setText(lessons.get(0).getName());
            }
        });
    }

}
