package pangian.car.studentdata.Lesson;

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
import pangian.car.studentdata.Lesson.RecView.LessonsAdapter;
import pangian.car.studentdata.R;

public class AllLessonsActivity extends AppCompatActivity {

    LessonViewModel lessonViewModel;
    private RecyclerView recyclerView;
    private LessonsAdapter lessonsAdapter;
  Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lessons);

        lessonViewModel = ViewModelProviders.of(this).get(LessonViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initRecView();
        getLessons();
        handleStudentClick();

    }

    private void getLessons() {
        lessonViewModel.getAllLessons().observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(List<Lesson> lessons) {
                lessonsAdapter.setLessons(lessons);
            }
        });
    }

    private void initRecView() {

        recyclerView=findViewById(R.id.lessons_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lessonsAdapter = new LessonsAdapter();
        recyclerView.setAdapter(lessonsAdapter);
    }

    private void handleStudentClick() {
        disposable = lessonsAdapter.getItemClickSignal().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer lessonId) throws Exception {
                goToLessonsDetails(lessonId);
            }
        });

    }

    private void goToLessonsDetails(int lessonId) {
        Intent intent = new Intent(AllLessonsActivity.this,EnrolledStudents.class);
        intent.putExtra("lesson_id_to_details",lessonId);
        startActivity(intent);
        finish();
    }
}
