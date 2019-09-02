package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.R;

public class StudentDetailsActivity extends AppCompatActivity {

    TextView studentName;
    TextView studentLesson;
    StudentViewModel studentViewModel;
    Button addLessonToStudentBtn;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        studentName = findViewById(R.id.student_details_name);
        studentLesson = findViewById(R.id.student_lesson_name);
        addLessonToStudentBtn  =findViewById(R.id.add_lesson_to_student_btn);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

        int studentAm = intent.getIntExtra("student_am_to_details", 0);
        prepareButtons(studentAm);
        getStudent(studentAm);
        getLessons(studentAm);
    }

    private void prepareButtons(int studentAm) {
        addLessonToStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetailsActivity.this,AddLessonToStudentActivity.class);
                intent.putExtra("student_am_to_add_lesson",studentAm);
                startActivity(intent);
            }
        });
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
