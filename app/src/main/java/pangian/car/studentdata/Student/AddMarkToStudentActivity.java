package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pangian.car.studentdata.R;

public class AddMarkToStudentActivity extends AppCompatActivity {

    EditText lessonMark;
    Button addMark;
    int lessonId;
    int studentAm;
    StudentViewModel studentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark_to_student);
        lessonMark = findViewById(R.id.lesson_mark_edit_text);
        addMark = findViewById(R.id.add_mark_to_lesson_btn);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();

        addMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lessonMark.getText()!=null && lessonId!=0){
                    setMark(Double.parseDouble(lessonMark.getText().toString()));
                }
            }
        });
    }


    private void getData() {
        Intent intent = getIntent();
        lessonId =  intent.getIntExtra("lesson_id_to_set_mark",0);
       studentAm =  intent.getIntExtra( "student_am_to_set_mark",0);
    }
    private void backToStudentDetails() {


        Intent intent = new Intent(AddMarkToStudentActivity.this,StudentDetailsActivity.class);
        startActivity(intent);
        finish();
    }

    private void setMark(Double mark) {
        studentViewModel.addMarkForLesson(studentAm ,lessonId,mark);
    }

}
