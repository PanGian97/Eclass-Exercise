package pangian.car.studentdata.Lesson;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "lesson_table")
public class Lesson {

    @PrimaryKey(autoGenerate = false)
    @NotNull
    @ColumnInfo(name="lesson_id")
    int id;
    @ColumnInfo(name="lesson_title")
    String name;

    public Lesson(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
