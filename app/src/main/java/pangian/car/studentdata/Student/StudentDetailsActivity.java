package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.Lesson.RecView.LessonsAdapter;
import pangian.car.studentdata.LessonEnrollment;
import pangian.car.studentdata.R;
import pangian.car.studentdata.RecView.LessonsEnrollmentAdapter;

public class StudentDetailsActivity extends AppCompatActivity {

    TextView studentName;
    private RecyclerView recyclerView;
    private LessonsEnrollmentAdapter lessonsAdapter;
    StudentViewModel studentViewModel;
    Button addLessonToStudentBtn;
    Disposable disposable;
    int studentAm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        studentName = findViewById(R.id.student_details_name);

        addLessonToStudentBtn = findViewById(R.id.add_lesson_to_student_btn);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
            studentAm = intent.getIntExtra("student_am_to_details", 0);
        prepareButtons(studentAm);
        getStudent(studentAm);
        initLessonsRecView();
        getLessons(studentAm);
        handleClicks();
    }


    private void prepareButtons(int studentAm) {
        addLessonToStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetailsActivity.this, AddLessonToStudentActivity.class);
                intent.putExtra("student_am_to_add_lesson", studentAm);
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


    private void initLessonsRecView() {
        recyclerView = findViewById(R.id.student_lessons_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lessonsAdapter = new LessonsEnrollmentAdapter();
        recyclerView.setAdapter(lessonsAdapter);
    }


    private void getLessons(int studentAm) {
        studentViewModel.getAllStudentLessons(studentAm).observe(this, new Observer<List<LessonEnrollment>>() {
            @Override
            public void onChanged(List<LessonEnrollment> studentLessons) {
                if (studentLessons.size() != 0) {
                    lessonsAdapter.setLessons(studentLessons);
                }
            }
        });
    }

    private void handleClicks() {
        disposable = lessonsAdapter.getItemClickSignal().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer lessonId) throws Exception {
                goToSetMark(lessonId);
            }
        });
    }

    private void goToSetMark(Integer lessonId) {
        Intent intent = new Intent(StudentDetailsActivity.this, AddMarkToStudentActivity.class);
        intent.putExtra("lesson_id_to_set_mark", lessonId);
        intent.putExtra("student_am_to_set_mark", studentAm);
        startActivity(intent);
    }
}
