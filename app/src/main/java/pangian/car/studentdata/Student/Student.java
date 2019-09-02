package pangian.car.studentdata.Student;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "student_table")
public class Student {

    @PrimaryKey(autoGenerate = false)
    @NotNull
    @ColumnInfo(name= "student_id")
    int id;
    @ColumnInfo(name= "student_name")
    String name;
    @ColumnInfo(name= "student_surname")
    String surname;

    public Student(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int am) {
        this.id = am;
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
