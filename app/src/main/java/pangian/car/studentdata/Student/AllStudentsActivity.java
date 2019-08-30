package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import pangian.car.studentdata.R;
import pangian.car.studentdata.Student.RecView.StudentsAdapter;

public class AllStudentsActivity extends AppCompatActivity {

    StudentViewModel studentViewModel;
    private RecyclerView recyclerView;
    private StudentsAdapter studentsAdapter;

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
        handleStudentClick();
    }

    private void handleStudentClick() {
       studentsAdapter.g
    }

    private void getStudents() {
        studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentsAdapter.setStudents(students);
            }
        });
    }

    private void initRecView() {
        recyclerView = findViewById(R.id.students_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentsAdapter = new StudentsAdapter();
        recyclerView.setAdapter(studentsAdapter);
    }
}
