package student.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsModel implements Serializable {

    private List<StudentModel> students;

    public StudentsModel() {
        this.students = new ArrayList<StudentModel>();
        
        try {
            this.loadStudents();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings({"unchecked"})
    public void loadStudents() throws IOException {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("students.dat"));
            try {
                List<StudentModel> savedStudents = (List<StudentModel>) input.readObject();
                this.students = savedStudents;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            input.close();
    }

    public void addStudent(StudentModel student) {
        this.students.add(student);
    }

    public void removeStudent(StudentModel student) {
        String id = student.getId();
        this.students = this.students.stream().filter(s -> s.getId() != id).collect(Collectors.toList());
    }

    public StudentModel getStudent(String id){
        for (StudentModel student : this.students) {
            if (student.getId().equals(id)) {
                return student;
            }
        };
        return null;
    }

    public List<StudentModel> getStudentList() {
        return this.students;
    }

}
