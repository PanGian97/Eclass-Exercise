package pangian.car.studentdata.Student.RecView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pangian.car.studentdata.R;



public class StudentsViewHolder extends RecyclerView.ViewHolder {

    TextView amTxt, nameTxt, surnameTxt;

    public StudentsViewHolder(@NonNull View itemView) {
        super(itemView);


        amTxt = (TextView) itemView.findViewById(R.id.student_item_id);
        nameTxt = (TextView) itemView.findViewById(R.id.student_item_name);
        surnameTxt = (TextView) itemView.findViewById(R.id.student_item_surname);

    }

}
