package pangian.car.studentdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import pangian.car.studentdata.Lesson.LessonActivity;
import pangian.car.studentdata.Student.AllStudentsActivity;
import pangian.car.studentdata.Student.StudentAdderActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton stdntLogin;
    ImageButton stdntSignup;
    ImageButton lessonAdder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stdntLogin = findViewById(R.id.student_login);
         stdntSignup = findViewById(R.id.student_signup);
         lessonAdder = findViewById(R.id.lesson_adder);
    }

    @Override
    protected void onStart() {
        super.onStart();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (v.getId()){

                    case R.id.student_login:
                         intent = new Intent(MainActivity.this, StudentAdderActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.student_signup:
                        intent = new Intent(MainActivity.this, AllStudentsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.lesson_adder:
                         intent = new Intent(MainActivity.this, LessonActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        stdntLogin.setOnClickListener(clickListener);
        stdntSignup.setOnClickListener(clickListener);
        lessonAdder.setOnClickListener(clickListener);
    }
}
