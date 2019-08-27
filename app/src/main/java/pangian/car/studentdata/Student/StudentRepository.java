package pangian.car.studentdata.Student;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pangian.car.studentdata.LocalDatabase;

class StudentRepository implements OnStudentValidationResult{


    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;
    MutableLiveData<Student> studentForVerification = ;


    public StudentRepository(Application application) {
        LocalDatabase database = LocalDatabase.getInstance(application);
        studentDao = database.studentDao();
    }

    public void insertStudent(Student student) {
        new InsertNoteAsyncTask(studentDao).execute(student);
    }


    public void deleteStudent(Student student) {
        new DeleteNoteAsyncTask(studentDao).execute(student);
    }

    public LiveData<List<Student>> getAllStudents() {
        return studentDao.getAllStudents();
    }



    public void checkIfStudentExists(Student student) {
        new validateStudent(studentDao).execute(student);
    }




    private static class InsertNoteAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private InsertNoteAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students[0]);
            return null;
        }
    }



    private static class DeleteNoteAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private DeleteNoteAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteStudent(students[0]);
            return null;
        }
    }


    private static class validateStudent extends AsyncTask<Student, Void, Student> {
        private StudentDao studentDao;
        private OnStudentValidationResult listener;
        public validateStudent(StudentDao studentDao,OnStudentValidationResult listener) {
            this.studentDao = studentDao;
            this.listener=listener;
        }



        @Override
        protected Student doInBackground(Student... students) {
               studentDao.isStudentValid(students[0].am);
               return students[0];
        }

        @Override
        protected void onPostExecute(Student student) {
            super.onPostExecute(student);
            listener.validateStudent(student);
        }
    }


    @Override
    public void validateStudent(Student student) {
       if(student.am)
    }

}
