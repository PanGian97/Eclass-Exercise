package pangian.car.studentdata.Student;

import androidx.annotation.Nullable;
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
import pangian.car.studentdata.LessonEnrollment;
import pangian.car.studentdata.R;
import pangian.car.studentdata.RecView.LessonsEnrollmentAdapter;

public class StudentDetailsActivity extends AppCompatActivity {

    private static final int ADD_LESSON_REQUEST = 1;
    private static final int ADD_MARK_TO_LESSON_REQUEST = 2;
    TextView studentName;
    private RecyclerView recyclerView;
    private LessonsEnrollmentAdapter lessonsAdapter;
    StudentViewModel studentViewModel;
    Button addLessonToStudentBtn;
    Disposable disposable;
    private int studentAm;
    private int lessonId;


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
        prepareAddLesson(studentAm);
        getStudent(studentAm);
        initLessonsRecView();
        getLessons(studentAm);
        handleClicks();
    }


    private void prepareAddLesson(int studentAm) {
        addLessonToStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetailsActivity.this, AddLessonToStudentActivity.class);
                startActivityForResult(intent,ADD_LESSON_REQUEST);//only for result

            }
        });
    }





    private void addLessonToStudent(int lessonToAddId) {
        studentViewModel.addStudentLesson(studentAm,lessonToAddId);
    }
    private void addMarkForLesson(double markToAdd) {
        studentViewModel.addMarkForLesson(studentAm,lessonId,markToAdd);
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
                goToSetMark();
                StudentDetailsActivity.this.lessonId = lessonId;
            }
        });
    }

    private void goToSetMark() {
        Intent intent = new Intent(StudentDetailsActivity.this, AddMarkToStudentActivity.class);
        startActivityForResult(intent,ADD_MARK_TO_LESSON_REQUEST);//only for result

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){
            case ADD_LESSON_REQUEST:
                if(resultCode == RESULT_OK){
                    assert data != null;
                    int lessonToAddId = data.getIntExtra(AddLessonToStudentActivity.LESSON_TO_ADD,0);
                    if(lessonToAddId!=0){
                        addLessonToStudent(lessonToAddId);
                    }
                }
                break;
            case ADD_MARK_TO_LESSON_REQUEST:
                if(resultCode == RESULT_OK){
                    assert data !=null;
                    double markToAdd = data.getDoubleExtra(AddMarkToStudentActivity.MARK_TO_ADD,0.0);
                    if(markToAdd!=0){
                        addMarkForLesson(markToAdd);
                    }
                }

        }

    }
}
