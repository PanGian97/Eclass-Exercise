package pangian.car.studentdata.Lesson;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import pangian.car.studentdata.R;

public class LessonAdderActivity extends AppCompatActivity {

    EditText lessonName;
    EditText lessonId;
    Button addLessonBtn;
    LessonViewModel lessonViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_adder);
        lessonName = findViewById(R.id.lesson_name);
        addLessonBtn = findViewById(R.id.add_lesson_btn);
        lessonId=findViewById(R.id.lesson_id);

        lessonViewModel = ViewModelProviders.of(this).get(LessonViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

        addLessonBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String id = lessonId.getText().toString();
                lessonViewModel.addLesson( Integer.parseInt(id),lessonName.getText().toString());
                }

        });
    }
}