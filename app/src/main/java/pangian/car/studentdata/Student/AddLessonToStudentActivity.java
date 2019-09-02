package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pangian.car.studentdata.R;

public class AddLessonToStudentActivity extends AppCompatActivity {

    EditText insertLesson;
    Button addLessonBtn;
    StudentViewModel studentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson_to_student);
        insertLesson = findViewById(R.id.lesson_to_be_added_to_student);
        addLessonBtn  =findViewById(R.id.add_lesson_to_student_btn);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();

        int studentAm = intent.getIntExtra("student_am_to_add_lesson", 0);


        addLessonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insertLesson.getText()!=null){
                    studentViewModel.addStudentLesson(studentAm);
                    insertLesson.getText()
                }
            }
        });
    }
}
