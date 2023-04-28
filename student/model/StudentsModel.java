package student.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsModel {

    private List<StudentModel> students;
    
    public StudentsModel() {
        this.students = new ArrayList<StudentModel>();
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
