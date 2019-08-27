package pangian.car.studentdata.Student.RecView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pangian.car.studentdata.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView titleTxt,descTxt;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        titleTxt = (TextView)itemView.findViewById(R.id.stdnt_name);
        descTxt = (TextView)itemView.findViewById(R.id.stdnt_surname);

    }

}
