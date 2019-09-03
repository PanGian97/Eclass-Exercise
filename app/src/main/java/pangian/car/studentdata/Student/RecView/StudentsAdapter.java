package pangian.car.studentdata.Student.RecView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pangian.car.studentdata.R;
import pangian.car.studentdata.Student.Student;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsViewHolder> {

    private List<Student> students = new ArrayList<>();
    private final PublishSubject<Integer> onClickSubject = PublishSubject.create();
    Student currentStudent;


    public Observable<Integer> getItemClickSignal() {

        return onClickSubject;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);

        return new StudentsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {

        currentStudent = students.get(position);
        holder.amTxt.setText(String.valueOf(currentStudent.getId()));
        holder.nameTxt.setText(currentStudent.getName());
        holder.surnameTxt.setText(currentStudent.getSurname());

        RxView.clicks(holder.itemView)
                .map(aVoid ->getStudentIdAt(position))//different from int position?
                .subscribe(onClickSubject);//?
    }

    //avoid memory leaks
    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        onClickSubject.onComplete();
    }

    public void setStudents(List<Student> students){
        this.students = students;
        notifyDataSetChanged();
    }

    public int getStudentIdAt(int position)//now we can get the note of the adapter to the outside
    {
        return students.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}