package pangian.car.studentdata.Student;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

import pangian.car.studentdata.Lesson.Lesson;

public interface OnStudentValidationResult {

  void validateStudent(int duplicates);


}
