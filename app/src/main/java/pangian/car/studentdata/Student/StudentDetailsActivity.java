package pangian.car.studentdata.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import pangian.car.studentdata.R;

public class StudentDetailsActivity extends AppCompatActivity {

    TextView studentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        studentName.findViewById(R.id.student_details_name);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
