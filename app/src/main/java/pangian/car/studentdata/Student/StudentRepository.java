package pangian.car.studentdata.Student;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pangian.car.studentdata.LocalDatabase;

class StudentRepository implements OnStudentValidationResult{


    private StudentDao studentDao;
    MutableLiveData<Student> studentForVerification = new MutableLiveData<>();
    MutableLiveData<String> messageToBeShown = new MutableLiveData<>();


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
        studentForVerification.setValue(student);
        new validateStudent(studentDao,this).execute(student);
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


    private static class validateStudent extends AsyncTask<Student, Void, Integer> {
        private StudentDao studentDao;
        private OnStudentValidationResult listener;
        public validateStudent(StudentDao studentDao,OnStudentValidationResult listener) {
            this.studentDao = studentDao;
            this.listener=listener;
        }



        @Override
        protected Integer doInBackground(Student... students) {
              return studentDao.isStudentValid(students[0].am);

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            listener.validateStudent(integer);
        }
    }


    @Override
    public void validateStudent(int duplicates) {
       if(duplicates==0){
           insertStudent(studentForVerification.getValue());
       }
       else{
           messageToBeShown.setValue("Student with this AM already exists");
       }
    }

   LiveData<String> messageHandler(){
        return messageToBeShown;
    }

}
