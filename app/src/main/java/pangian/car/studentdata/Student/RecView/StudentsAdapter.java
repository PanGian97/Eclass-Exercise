package pangian.car.studentdata.Student.RecView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pangian.car.studentdata.R;
import pangian.car.studentdata.Student.Student;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsViewHolder> {

    private List<Student> students = new ArrayList<>();

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {

        Student currentStudent = students.get(position);
        holder.amTxt.setText(String.valueOf(currentStudent.getId()));
        holder.nameTxt.setText(currentStudent.getName());
        holder.surnameTxt.setText(currentStudent.getSurname());
    }

    public void setStudents(List<Student> students){
        this.students = students;
        notifyDataSetChanged();
    }

    public Student getStudentAt(int position)//now we can get the note of the adapter to the outside
    {
        return students.get(position);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}