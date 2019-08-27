package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import pangian.car.studentdata.R;
import pangian.car.studentdata.Student.RecView.MyAdapter;

public class AllStudentsActivity extends AppCompatActivity {

    StudentViewModel studentViewModel;
    private RecyclerView recyclerView;
    private MyAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initRecView();
        getStudents();
    }

    private void getStudents() {
        studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentAdapter.setStudents(students);
            }
        });
    }

    private void initRecView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        studentAdapter = new MyAdapter();
        recyclerView.setAdapter(studentAdapter);
    }
}
