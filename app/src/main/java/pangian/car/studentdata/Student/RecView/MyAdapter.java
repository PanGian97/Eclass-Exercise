package pangian.car.studentdata.Student.RecView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pangian.car.studentdata.R;
import pangian.car.studentdata.Student.Student;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Student> students = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Student currentStudent = students.get(position);
        holder.titleTxt.setText(currentStudent.getName());
        holder.descTxt.setText(currentStudent.getSurname());
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