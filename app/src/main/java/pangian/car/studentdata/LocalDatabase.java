package pangian.car.studentdata;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.Lesson.LessonDao;
import pangian.car.studentdata.Student.Student;
import pangian.car.studentdata.Student.StudentDao;

@Database(entities = {Student.class, Lesson.class}, version = 2 )
public abstract class LocalDatabase extends RoomDatabase {


    //Singleton
    private static LocalDatabase instance;//cant create multiple instances of current DB

    public abstract StudentDao studentDao(); //similar to metods to Dao interface...We later use this method to acces our Dao
    public abstract LessonDao lessonDao();

    public static synchronized LocalDatabase getInstance(Context context)//synchronized so only one thread at a time can access it/?
    {
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),LocalDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()//delete db schema and recreate it in case db changes or version changes
                    .addCallback(roomCallback)
                    .build();//creates the instance of the DB
        }
        return instance;

    }
    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbASyncTask(instance).execute();
        }
    };
    private static class PopulateDbASyncTask extends AsyncTask<Void,Void,Void>
    {
        private StudentDao studentDao;
        private LessonDao lessonDao;
        private PopulateDbASyncTask(LocalDatabase db)
        {
            studentDao = db.studentDao();
            lessonDao = db.lessonDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.insertStudent(new Student(15445,"Panagiotis","Giannelos"));
            lessonDao.insertLesson(new Lesson("Ma8imatika"));
            return null;
        }
    }

}
