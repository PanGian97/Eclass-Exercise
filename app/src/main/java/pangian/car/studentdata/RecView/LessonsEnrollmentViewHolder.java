package pangian.car.studentdata.RecView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pangian.car.studentdata.R;


public class LessonsEnrollmentViewHolder extends RecyclerView.ViewHolder {

    TextView idTxt,titleTxt,markTxt;

    public LessonsEnrollmentViewHolder(@NonNull View itemView) {
        super(itemView);

        idTxt = (TextView)itemView.findViewById(R.id.enr_lesson_item_id);
        titleTxt = (TextView)itemView.findViewById(R.id.enr_lesson_item_name);
        markTxt = (TextView)itemView.findViewById(R.id.enr_lesson_item_mark);
    }

}
