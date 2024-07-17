package University.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import University.Model.Student;
import University.Service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewHomePage(Model model) {
        List<Student> listStudent = service.listAll();
        model.addAttribute("listStudent", listStudent);
        return "display";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String editStudent(@RequestParam("id") long id, Model model) {
        Student std = service.get(id);
        model.addAttribute("student", std);
        return "update";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public String department() {
        return "department";
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public String sort() {
        return "sort";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(
            @Validate @ModelAttribute("student") Student std,
            BindingResult bindingResult,
            @RequestParam("student_id") String student_id,
            @RequestParam("first_name") String first_name,
            @RequestParam("last_name") String last_name,
            @RequestParam("department") String department,
            @RequestParam("gender") String gender,
            @RequestParam("gpa") String gpa,
            @RequestParam("level") String level,
            @RequestParam("address") String address,
            Model model) throws Exception {

        try {
            service.validateField("student_id", student_id, bindingResult);
            service.validateField("first_name", first_name, bindingResult);
            service.validateField("last_name", last_name, bindingResult);
            service.validateField("department", department, bindingResult);
            service.validateField("gender", gender, bindingResult);
            service.validateField("gpa", gpa, bindingResult);
            service.validateField("level", level, bindingResult);
            service.validateField("address", address, bindingResult);

            if (bindingResult.hasErrors()) {
                return "add";
            }

            Long student_idValue = Long.parseLong(student_id);
            Double gpaValue = Double.parseDouble(gpa);
            Long levelValue = Long.parseLong(level);

            Student student = new Student();

            student.setStudent_id(student_idValue);
            student.setFirst_name(first_name.toLowerCase());
            student.setLast_name(last_name.toLowerCase());
            student.setDepartment(department.toLowerCase());
            student.setGender(gender.toLowerCase());
            student.setGpa(gpaValue);
            student.setLevel(levelValue);
            student.setAddress(address.toLowerCase());
            service.save(student);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "An error occurred while adding student id " + student_id + ". Try again!");
            return "add";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("search") String search, @RequestParam("search_value") String value,
            Model model) throws Exception {
        try {
            System.out.println(search + " " + value);
            if (service.areFieldsValid(value)) {
                if (!((search.equals("Student ID") || search.equals("GPA") || search.equals("Level"))
                        && service.areFieldsString(value))) {
                    List<Student> listStudent = service.search(search, value);
                    if (listStudent.isEmpty()) {
                        model.addAttribute("errorMessage",
                                "There is no student with " + search + " equal " + value + ". Try again!");
                    }
                    model.addAttribute("listStudent", listStudent);
                } else {
                    model.addAttribute("errorMessage", "Please enter numeric value. Try again!");
                }
            } else {
                model.addAttribute("errorMessage", "Please fill in the value without spaces. Try again!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "An error occurred while searching for " + search + ". Try again!");
        }
        return "search";
    }

    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public String departmentStudents(
            @Validate @ModelAttribute("student") Student std,
            BindingResult bindingResult,
            @RequestParam("student_id") String student_id,
            @RequestParam("department") String department,
            Model model) throws Exception {
        try {
            if (service.areFieldsValid(student_id)) {
                if (service.areFieldsValidNumeric(student_id)) {
                    Student student = service.getStudentByStudentId(Long.parseLong(student_id));
                    if (student != null) {
                        if (service.isLevelLarge(student.getLevel())) {
                            if (student.getDepartment().equals("not available")) {
                                student.setDepartment(department);
                                service.save(student);
                                return "redirect:/";
                            } else {
                                model.addAttribute("errorMessage",
                                        "The student id " + student_id
                                                + " has already chosen the department. Try again!");
                                return "department";
                            }
                        } else {
                            model.addAttribute("errorMessage",
                                    "The level of the student id " + student_id
                                            + " less than 3, can't change the department. Try again!");
                            return "department";
                        }
                    } else {
                        model.addAttribute("errorMessage", "Student id " + student_id + " not exists. Try again!");
                        return "department";
                    }
                } else {
                    model.addAttribute("errorMessage",
                            "The student id should be a numeric value and greater than 0. Try again!");
                    return "department";
                }
            } else {
                model.addAttribute("errorMessage", "The student id is an empty. Try again!");
                return "department";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "An error occurred while selecting the department for the student id " + student_id
                            + ". Try again!");
            return "department";
        }
    }

    @RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.POST })
    public String editStudentPage(
            @Validate @ModelAttribute("student") Student std,
            BindingResult bindingResult,
            @RequestParam("id") long id,
            @RequestParam("student_id") String student_id,
            @RequestParam("first_name") String first_name,
            @RequestParam("last_name") String last_name,
            @RequestParam("department") String department,
            @RequestParam("gender") String gender,
            @RequestParam("gpa") String gpa,
            @RequestParam("level") String level,
            @RequestParam("address") String address,
            Model model) {

        service.validateField("first_name", first_name, bindingResult);
        service.validateField("last_name", last_name, bindingResult);
        service.validateField("department", department, bindingResult);
        service.validateField("gender", gender, bindingResult);
        service.validateField("gpa", gpa, bindingResult);
        service.validateField("level", level, bindingResult);
        service.validateField("address", address, bindingResult);

        if (bindingResult.hasErrors()) {
            return "update";
        }

        try {
            Long student_idValue = Long.parseLong(student_id);
            Double gpaValue = Double.parseDouble(gpa);
            Long levelValue = Long.parseLong(level);
            Student oldStudent = service.getStudentByStudentId(student_idValue);
            if (oldStudent != null) {
                if (service.isDataChanged(oldStudent, first_name, last_name, department, gender, gpaValue, levelValue,
                        address)) {
                    if (service.departmentChanged(student_idValue, department)) {
                        if (levelValue < 3) {
                            model.addAttribute("errorMessage",
                                    "The level of the student is less than 3, can't change the department. Try again!");
                            return "update";
                        }
                    }

                    Student student = service.getStudentByStudentId(student_idValue);
                    student.setFirst_name(first_name.toLowerCase());
                    student.setLast_name(last_name.toLowerCase());
                    student.setDepartment(department.toLowerCase());
                    student.setGender(gender.toLowerCase());
                    student.setGpa(gpaValue);
                    student.setLevel(levelValue);
                    student.setAddress(address.toLowerCase());
                    service.save(student);

                    return "redirect:/";
                } else {
                    model.addAttribute("errorMessage", "There was no change in the data. Try again!");
                    return "update";
                }
            } else {
                model.addAttribute("errorMessage", "Student id " + student_id + " not found. Try again!");
                return "update";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "An error occurred while updating student id " + student_id + ". Try again!");
            return "update";
        }
    }

    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public String sortStudents(
            String sort,
            String order,
            Model model) throws Exception {
        try {
            List<Student> listStudent = service.sort(sort, order);
            if (listStudent.isEmpty()) {
                model.addAttribute("errorMessage", "There is no students. Try again!");
            }
            model.addAttribute("listStudent", listStudent);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage",
                    "An error occurred while sorting the students. Try again!");
        }
        return "sort";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable(name = "id") long id) {
        service.delete(id);
        return "redirect:/";
    }
}
