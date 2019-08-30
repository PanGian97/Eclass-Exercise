package pangian.car.studentdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import pangian.car.studentdata.Lesson.AllLessonsActivity;
import pangian.car.studentdata.Lesson.LessonAdderActivity;
import pangian.car.studentdata.Student.AllStudentsActivity;
import pangian.car.studentdata.Student.StudentAdderActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton studentsAdder;
    ImageButton studentView;
    ImageButton lessonsAdder;
    ImageButton lessonsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentsAdder = findViewById(R.id.students_adder);
         studentView = findViewById(R.id.students_view);
         lessonsAdder = findViewById(R.id.lessons_adder);
         lessonsView = findViewById(R.id.lessons_view);
    }

    @Override
    protected void onStart() {
        super.onStart();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (v.getId()){

                    case R.id.students_adder:
                         intent = new Intent(MainActivity.this, StudentAdderActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.students_view:
                        intent = new Intent(MainActivity.this, AllStudentsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.lessons_adder:
                         intent = new Intent(MainActivity.this, LessonAdderActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.lessons_view:
                        intent = new Intent(MainActivity.this, AllLessonsActivity.class);
                        startActivity(intent);
                }
            }
        };

        studentsAdder.setOnClickListener(clickListener);
        studentView.setOnClickListener(clickListener);
        lessonsAdder.setOnClickListener(clickListener);
        lessonsView.setOnClickListener(clickListener);
    }
}
