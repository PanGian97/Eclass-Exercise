package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.Lesson.LessonViewModel;
import pangian.car.studentdata.Lesson.RecView.LessonsAdapter;
import pangian.car.studentdata.R;

public class AddLessonToStudentActivity extends AppCompatActivity {


    public static final String LESSON_TO_ADD = "lesson_to_add";
    Disposable disposable;
    LessonViewModel lessonViewModel;
    private int studentAm;
    private RecyclerView recyclerView;
    private LessonsAdapter lessonsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson_to_student);



        lessonViewModel  = ViewModelProviders.of(this).get(LessonViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initLessons();


    }

    private void initLessons() {
        recyclerView=findViewById(R.id.lessons_to_add_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lessonsAdapter = new LessonsAdapter();
        recyclerView.setAdapter(lessonsAdapter);
        getLessons();
        handleClicks();
    }


    private void getLessons() {
        lessonViewModel.getAllLessons().observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(List<Lesson> lessons) {
                lessonsAdapter.setLessons(lessons);
            }
        });
    }


    private void handleClicks() {
        disposable = lessonsAdapter.getItemClickSignal().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer lessonId) throws Exception {
                goBackToAddLessonToStudent(lessonId);
            }
        });
    }





    private void goBackToAddLessonToStudent(Integer lessonId) {

        Intent intent = new Intent(AddLessonToStudentActivity.this,StudentDetailsActivity.class);
        intent.putExtra(LESSON_TO_ADD,lessonId);
        setResult(RESULT_OK,intent);
        finish();
    }
}
