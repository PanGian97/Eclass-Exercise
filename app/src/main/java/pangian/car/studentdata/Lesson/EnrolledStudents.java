package pangian.car.studentdata.Lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import io.reactivex.disposables.Disposable;
import pangian.car.studentdata.LessonEnrollment;
import pangian.car.studentdata.R;
import pangian.car.studentdata.Student.StudentDetailsActivity;
import pangian.car.studentdata.Student.StudentViewModel;

public class EnrolledStudents extends AppCompatActivity {
    private RecyclerView recyclerView;
   // private StudentsEnrolledAdapter enrolledAdapter;
    StudentViewModel studentViewModel;
    Disposable disposable;
    int lessonId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_students);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        lessonId = intent.getIntExtra("lesson_id_to_details", 0);
        initMessageHandler();
    //    initStudentsRecView();
    }

    private void initMessageHandler() {

        studentViewModel.messageHandler().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Toast.makeText(EnrolledStudents.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void initStudentsRecView() {
//        recyclerView = findViewById(R.id.enrolled_students_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        enrolledAdapter = new StudentsEnrolledAdapter();
//        recyclerView.setAdapter(enrolledAdapter);
//    }
//
//    private void getLessons(int studentAm) {
//        studentViewModel.getAllStudentLessons(studentAm).observe(this, new Observer<List<LessonEnrollment>>() {
//            @Override
//            public void onChanged(List<LessonEnrollment> studentLessons) {
//                if (studentLessons.size() != 0) {
//                    lessonsAdapter.setLessons(studentLessons);
//                }
//            }
//        });
//    }
}
