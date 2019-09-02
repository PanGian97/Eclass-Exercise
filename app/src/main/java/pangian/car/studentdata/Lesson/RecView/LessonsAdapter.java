package pangian.car.studentdata.Lesson.RecView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.R;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsViewHolder> {

    private List<Lesson> lessons = new ArrayList<>();

    @NonNull
    @Override
    public LessonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_iten, parent, false);
        return new LessonsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsViewHolder holder, int position) {

        Lesson currentLesson = lessons.get(position);
        holder.idTxt.setText(String.valueOf(currentLesson.getId()));
        holder.titleTxt.setText(currentLesson.getName());

    }

    public void setLessons(List<Lesson> lessons){
        this.lessons = lessons;
        notifyDataSetChanged();
    }

    public Lesson getLessonAt(int position)//now we can get the note of the adapter to the outside
    {
        return lessons.get(position);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }
}