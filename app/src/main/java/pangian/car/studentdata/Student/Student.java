package pangian.car.studentdata.Student;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student {

    @PrimaryKey(autoGenerate = false)
    int am;

    String name;

    String surname;

    public Student( int am , String name, String surname) {
        this.am = am;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return am;
    }

    public void setId(int am) {
        this.am = am;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
