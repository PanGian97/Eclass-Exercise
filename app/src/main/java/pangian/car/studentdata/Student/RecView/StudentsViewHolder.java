package pangian.car.studentdata.Student.RecView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pangian.car.studentdata.R;
import pangian.car.studentdata.Student.Student;


public class StudentsViewHolder extends RecyclerView.ViewHolder {

    TextView amTxt, nameTxt, surnameTxt;








    public StudentsViewHolder(@NonNull View itemView) {
        super(itemView);

        RxView.clicks(itemView)
                .map(__ -> getAdapterPosition())
                .subscribe(onClickSubject);

        amTxt = (TextView) itemView.findViewById(R.id.student_item_id);
        nameTxt = (TextView) itemView.findViewById(R.id.student_item_name);
        surnameTxt = (TextView) itemView.findViewById(R.id.student_item_surname);

    }

}
