package com.example.btl_nhom7.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.btl_nhom7.model.Assignment;
import com.example.btl_nhom7.model.DetailedAssignment;
import com.example.btl_nhom7.model.Student;
import com.example.btl_nhom7.model.Teacher;
import java.util.ArrayList;


public class SqlHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "TrucNhat.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_STUDENT = "Student";
    private static final String TABLE_TEACHER = "Teacher";
    private static final String TABLE_ROOM = "Room";
    private static final String TABLE_CLASS_STUDENT = "ClassStudent";
    private static final String TABLE_CLASS = "Class";
    private static final String TABLE_ASSIGNMENT = "Assignment";


    public SqlHelper(@Nullable Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
        db.execSQL("CREATE TABLE " + TABLE_STUDENT + " (" +
                "idStudent TEXT PRIMARY KEY, " +
                "Name TEXT, " +
                "Password TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_TEACHER + " (" +
                "ID TEXT PRIMARY KEY, " +
                "Name TEXT, " +
                "Password TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_ROOM + " (" +
                "ID TEXT PRIMARY KEY, " +
                "Name TEXT, " +
                "Devices TEXT, " +
                "Task TEXT, " +
                "Method INTEGER);");

        db.execSQL("CREATE TABLE " + TABLE_CLASS + " (" +
                "ID TEXT PRIMARY KEY, " +
                "Name TEXT, " +
                "TeacherID TEXT, " +
                "FOREIGN KEY (TeacherID) REFERENCES " + TABLE_TEACHER + "(ID));");

        db.execSQL("CREATE TABLE " + TABLE_CLASS_STUDENT + " (" +
                "idStudent TEXT, " +
                "idClass TEXT, " +
                "rating INTEGER, " +
                "className TEXT, " +
                "PRIMARY KEY (idStudent, idClass), " +
                "FOREIGN KEY (idStudent) REFERENCES " + TABLE_STUDENT + "(idStudent), " +
                "FOREIGN KEY (idClass) REFERENCES " + TABLE_CLASS + "(ID));");

        db.execSQL("CREATE TABLE " + TABLE_ASSIGNMENT + " (" +
                "ClassID TEXT, " +
                "Day TEXT, " +
                "StartTime TEXT, " +
                "EndTime TEXT, " +
                "RoomID TEXT, " +
                "TeacherID TEXT, " +
                "PRIMARY KEY (ClassID, Day, StartTime, RoomID), " +
                "FOREIGN KEY (ClassID) REFERENCES " + TABLE_CLASS + "(ID), " +
                "FOREIGN KEY (TeacherID) REFERENCES " + TABLE_TEACHER + "(ID), " +
                "FOREIGN KEY (RoomID) REFERENCES " + TABLE_ROOM + "(ID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENT);
        onCreate(db);

    }

    public void insertSampleData(SQLiteDatabase db) {
        // Insert data into the Student table
        db.execSQL("INSERT INTO " + TABLE_STUDENT + " (idStudent, Name, Password) VALUES ('2021602743', 'Ly Hai Hung', '123123');");
        db.execSQL("INSERT INTO " + TABLE_STUDENT + " (idStudent, Name, Password) VALUES ('2021602333', 'Tran Minh Hieu', '123123');");

        // Insert data into the Teacher table
        db.execSQL("INSERT INTO " + TABLE_TEACHER + " (ID, Name, Password) VALUES ('T01', 'Vu Thi Duong', '123123');");

        // Insert data into the Room table
        db.execSQL("INSERT INTO " + TABLE_ROOM + " (ID, Name, Devices, Task, Method) VALUES ('R01', 'Phong 101', 'Projector, Whiteboard', 'Lecture', 1);");

        // Insert data into the Class table
        db.execSQL("INSERT INTO " + TABLE_CLASS + " (ID, Name, TeacherID) VALUES ('C01', 'Lop 12A1', 'T01');");

        // Insert data into the ClassStudent table, note the inclusion of className and rating.
        db.execSQL("INSERT INTO " + TABLE_CLASS_STUDENT + " (idStudent, idClass, rating, className) VALUES ('2021602743', 'C01', 1, 'Lop 12A1');");
        db.execSQL("INSERT INTO " + TABLE_CLASS_STUDENT + " (idStudent, idClass, rating, className) VALUES ('2021602333', 'C01', 1, 'Lop 12A1');");

        // Insert data into the Assignment table
        db.execSQL("INSERT INTO " + TABLE_ASSIGNMENT + " (ClassID, Day, StartTime, EndTime, RoomID, TeacherID) VALUES ('C01', '20-10-2023', '08:00', '10:00', 'R01', 'T01');");
    }


    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String password = cursor.getString(2);
                students.add(new Student(id, password, name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return students;
    }

    public ArrayList<Teacher> getAllTeacher(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TEACHER, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String password = cursor.getString(2);
                teachers.add(new Teacher(id, name, password));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return teachers;
    }

    public ArrayList<Assignment> getAllAssignmentOfClass(String classID){
        ArrayList<Assignment> assignments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a SQL query to retrieve all assignments
        String query = "SELECT * FROM " + TABLE_ASSIGNMENT + " WHERE ClassID = '" + classID + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String classId = cursor.getString(0);
                String day = cursor.getString(1);
                String startTime = cursor.getString(2);
                String endTime = cursor.getString(3);
                String roomId = cursor.getString(4);
                String teacherId = cursor.getString(5);

                // Create an Assignment object and add it to the list
                assignments.add(new Assignment(classId,day, startTime, endTime, roomId, teacherId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();  // Don't forget to close the database and cursor
        return assignments;
    }
    public Student checkStudentLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT + " WHERE idStudent = ? AND Password = ?", new String[]{username, password});
        if (cursor.moveToFirst()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String pwd = cursor.getString(2);
            cursor.close();
            db.close();
            return new Student(id, name, pwd);
        }
        cursor.close();
        return null;
    }
    public ArrayList<String> getClassOfStudent(String studentID){
        ArrayList<String> listID = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Đảm bảo sử dụng đúng tên cột "idClass" thay vì "ClassID"
        Cursor cursor = db.rawQuery("SELECT idClass FROM " + TABLE_CLASS_STUDENT + " WHERE idStudent = ?", new String[]{studentID});
        if (cursor.moveToFirst()) {
            do {
                String idClass = cursor.getString(0);
                listID.add(idClass);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listID;
    }

    // Method to check teacher credentials
    public Teacher checkTeacherLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TEACHER + " WHERE ID = ? AND Password = ?", new String[]{username, password});
        if (cursor.moveToFirst()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String pwd = cursor.getString(2);
            cursor.close();
            db.close();
            return new Teacher(id, name, pwd);
        }
        cursor.close();
        db.close();
        return null;
    }

    public ArrayList<Student> getStudentsByStatusAndClass(int status, String classId) {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT Student.ID, Student.Name, Student.Password, Student.Rating FROM Student " +
                "INNER JOIN ClassStudent ON Student.ID = ClassStudent.StudentID " +
                "WHERE Student.Rating = ? AND ClassStudent.ClassID = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(status), classId});

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String password = cursor.getString(2);
                students.add(new Student(id, password, name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }

    public ArrayList<Student> getStudentsInClassWithRating(String classId, int rating) {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Ensure your query correctly handles the inner join and where conditions
        Cursor cursor = db.rawQuery("SELECT Student.idStudent, Student.Password, Student.Name FROM Student INNER JOIN ClassStudent ON Student.idStudent = ClassStudent.idStudent WHERE ClassStudent.idClass = ? AND ClassStudent.rating = ?", new String[]{classId,  String.valueOf(rating)});

        while (cursor.moveToNext()) {
            String idStudent = cursor.getString(0);
            String name = cursor.getString(2);
            String password = cursor.getString(1);
            // Assuming Constructor Student(idStudent, name, password)
            students.add(new Student(idStudent, name, password));
        }
        cursor.close();
        db.close();
        return students;
    }
    public DetailedAssignment getDetailedAssignment(String classID, String roomID, String startTime) {
        SQLiteDatabase db = this.getReadableDatabase();
        DetailedAssignment detailedAssignment = null;

        String query = "SELECT Class.Name, Assignment.Day, Assignment.StartTime, Assignment.EndTime, Room.Name, Room.Method " +
                "FROM Assignment " +
                "JOIN Class ON Assignment.ClassID = Class.ID " +
                "JOIN Room ON Assignment.RoomID = Room.ID " +
                "WHERE Assignment.ClassID = ? AND Assignment.RoomID = ? AND Assignment.StartTime = ?";

        Cursor cursor = db.rawQuery(query, new String[]{classID, roomID, startTime});
        if (cursor.moveToFirst()) {
            String className = cursor.getString(0);
            String day = cursor.getString(1);
            String start = cursor.getString(2);
            String end = cursor.getString(3);
            String roomName = cursor.getString(4);
            int method = cursor.getInt(5);


            detailedAssignment = new DetailedAssignment(className, day, start, end, roomName, method);
        }
        cursor.close();
        db.close();
        return detailedAssignment;
    }

    public ArrayList<String> getClassesOfTeacher(String teacherId) {
        ArrayList<String> classIDs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Định nghĩa truy vấn SQL để lấy tất cả ID lớp học mà giáo viên này dạy
        String query = "SELECT ID FROM " + TABLE_CLASS + " WHERE TeacherID = ?";
        Cursor cursor = db.rawQuery(query, new String[]{teacherId});

        // Lặp qua kết quả và thêm vào danh sách
        if (cursor.moveToFirst()) {
            do {
                String classId = cursor.getString(0); // Lấy ID của lớp
                classIDs.add(classId);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return classIDs;
    }
}
