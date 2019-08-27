package pangian.car.studentdata.Student;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pangian.car.studentdata.R;

public class StudentAdderActivity extends AppCompatActivity {

    EditText stdntName;
    EditText stdntSurName;
    EditText stdntAm;
    Button addBtn;
    StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        stdntAm = findViewById(R.id.am);
        stdntSurName = findViewById(R.id.surname);
        stdntName = findViewById(R.id.name);
        addBtn = findViewById(R.id.add_btn);

    }

    @Override
    protected void onStart() {
        super.onStart();

        addBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String am = stdntAm.getText().toString();
                studentViewModel.addStudent(
                        Integer.parseInt(am),
                        stdntName.getText().toString(),
                        stdntSurName.getText().toString());
                {

                }
            }
        });


    }
}
