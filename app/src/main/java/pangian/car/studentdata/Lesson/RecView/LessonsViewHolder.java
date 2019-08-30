package pangian.car.studentdata.Lesson.RecView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pangian.car.studentdata.R;


public class LessonsViewHolder extends RecyclerView.ViewHolder {

    TextView idTxt,titleTxt;

    public LessonsViewHolder(@NonNull View itemView) {
        super(itemView);

        idTxt = (TextView)itemView.findViewById(R.id.lesson_item_id);
        titleTxt = (TextView)itemView.findViewById(R.id.lesson_item_name);
    }

}
