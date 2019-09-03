package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pangian.car.studentdata.R;

public class AddMarkToStudentActivity extends AppCompatActivity {

    public static final String MARK_TO_ADD = "mark_to_add" ;
    EditText lessonMark;
    Button addMark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark_to_student);
        lessonMark = findViewById(R.id.lesson_mark_edit_text);
        addMark = findViewById(R.id.add_mark_to_lesson_btn);

    }

    @Override
    protected void onStart() {
        super.onStart();


        addMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lessonMark.getText()!=null){
                    goBackToSetMark(Double.parseDouble(lessonMark.getText().toString()));
                }
            }
        });
    }

    private void goBackToSetMark(Double mark) {
        Intent intent = new Intent(AddMarkToStudentActivity.this,StudentDetailsActivity.class);
        intent.putExtra(MARK_TO_ADD,mark);
        setResult(RESULT_OK,intent);
        finish();
    }

}
