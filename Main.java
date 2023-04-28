import student.controller.StudentController;
import student.model.StudentsModel;
import student.view.StudentView;


public class Main {
    public static void main(String[] args) {
        StudentsModel model = new StudentsModel();
        StudentView view = new StudentView(model.getStudentList());
        StudentController controller = new StudentController(model, view);
    }
}