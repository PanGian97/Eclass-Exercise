package pangian.car.studentdata.RecView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.LessonEnrollment;
import pangian.car.studentdata.R;

public class LessonsEnrollmentAdapter extends RecyclerView.Adapter<LessonsEnrollmentViewHolder> {

    private List<LessonEnrollment> lessons = new ArrayList<>();
    private LessonEnrollment currentLesson;
    private final PublishSubject<Integer> onClickSubject = PublishSubject.create();
    @NonNull
    @Override
    public LessonsEnrollmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.enr_lesson_item, parent, false);



        return new LessonsEnrollmentViewHolder(itemView);
    }

    public Observable<Integer> getItemClickSignal() {
        return onClickSubject;
    }


    @Override
    public void onBindViewHolder(@NonNull LessonsEnrollmentViewHolder holder, int position) {

         currentLesson = lessons.get(position);
        holder.idTxt.setText(String.valueOf(currentLesson.getLessonId()));
        holder.titleTxt.setText(currentLesson.getLessonTitle());
        holder.markTxt.setText(String.valueOf(currentLesson.getLessonMark()));


        RxView.clicks(holder.itemView)
                .map(aVoid -> getLessonIdAt(position))
                .subscribe(onClickSubject);//?
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        onClickSubject.onComplete();
    }

    public void setLessons(List<LessonEnrollment> lessons){
        this.lessons = lessons;
        notifyDataSetChanged();
    }

    public int getLessonIdAt(int position)//now we can get the note of the adapter to the outside
    {
        return lessons.get(position).getLessonId();
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }


}