package University.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import University.Model.Student;
import University.Repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;

	public List<Student> listAll() {
		return repo.findAll();
	}

	public void save(Student std) {
		repo.save(std);
	}

	public Student get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<Student> search(String search, String search_value) throws Exception {

		List<Student> searchResults = new ArrayList<>();
		switch (search) {
			case "Student ID":
				searchResults = SearchByStudentId(Long.parseLong(search_value));
				break;
			case "First Name":
				searchResults = SearchByFirstName(search_value.toLowerCase());
				break;
			case "Last Name":
				searchResults = SearchByLastName(search_value.toLowerCase());
				break;
			case "Department":
				searchResults = SearchByDepartment(search_value.toLowerCase());
				break;
			case "Gender":
				searchResults = SearchByGender(search_value.toLowerCase());
				break;
			case "GPA":
				searchResults = SearchByGPA(Double.parseDouble(search_value));
				break;
			case "Level":
				searchResults = SearchByLevel(Long.parseLong(search_value));
				break;
			case "Address":
				searchResults = SearchByAddress(search_value.toLowerCase());
				break;
			default:
				throw new IllegalArgumentException("Invalid attribute for searching: " + search);
		}
		return searchResults;
	}

	public List<Student> SearchByStudentId(Long student_id) throws Exception {

		List<Student> allStudents = repo.findAll();
		List<Student> foundStudents = new ArrayList<>();
		for (Student stu : allStudents) {
			if (stu.getStudent_id() == student_id)
				foundStudents.add(stu);
		}
		return foundStudents;
	}

	public List<Student> SearchByFirstName(String first_name) throws Exception {

		List<Student> allStudents = repo.findAll();
		List<Student> foundStudents = new ArrayList<>();
		for (Student stu : allStudents) {
			if (stu.getFirst_name().equals(first_name))
				foundStudents.add(stu);
		}
		return foundStudents;
	}

	public List<Student> SearchByLastName(String last_name) throws Exception {

		List<Student> allStudents = repo.findAll();
		List<Student> foundStudents = new ArrayList<>();
		for (Student stu : allStudents) {
			if (stu.getLast_name().equals(last_name))
				foundStudents.add(stu);
		}
		return foundStudents;
	}

	public List<Student> SearchByDepartment(String department) throws Exception {

		List<Student> allStudents = repo.findAll();
		List<Student> foundStudents = new ArrayList<>();
		for (Student stu : allStudents) {
			if (stu.getDepartment().equals(department))
				foundStudents.add(stu);
		}
		return foundStudents;
	}

	public List<Student> SearchByGender(String gender) throws Exception {

		List<Student> allStudents = repo.findAll();
		List<Student> foundStudents = new ArrayList<>();
		for (Student stu : allStudents) {
			if (stu.getGender().equals(gender))
				foundStudents.add(stu);
		}
		return foundStudents;
	}

	public List<Student> SearchByGPA(Double gpa) throws Exception {

		List<Student> allStudents = repo.findAll();
		List<Student> foundStudents = new ArrayList<>();
		for (Student stu : allStudents) {
			if (stu.getGpa() == gpa)
				foundStudents.add(stu);
		}
		return foundStudents;
	}

	public List<Student> SearchByLevel(Long level) throws Exception {

		List<Student> allStudents = repo.findAll();
		List<Student> foundStudents = new ArrayList<>();
		for (Student stu : allStudents) {
			if (stu.getLevel() == level)
				foundStudents.add(stu);
		}
		return foundStudents;
	}

	public List<Student> SearchByAddress(String address) throws Exception {

		List<Student> allStudents = repo.findAll();
		List<Student> foundStudents = new ArrayList<>();
		for (Student stu : allStudents) {
			if (stu.getAddress().equals(address))
				foundStudents.add(stu);
		}
		return foundStudents;
	}

	public Student getStudentByStudentId(Long student_id) {
		List<Student> allStudents = repo.findAll();
		Student foundStudent = null;
		for (Student stu : allStudents) {
			try {
				if (stu.getStudent_id() == student_id) {
					foundStudent = stu;
					break;
				}
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return foundStudent;
	}

	public List<Student> sort(String sort, String order) throws Exception {
		List<Student> students = repo.findAll();

		switch (sort) {
			case "student_id":
				students.sort(Comparator.comparing(Student::getStudent_id));
				break;
			case "first_name":
				students.sort(Comparator.comparing(Student::getFirst_name));
				break;
			case "last_name":
				students.sort(Comparator.comparing(Student::getLast_name));
				break;
			case "department":
				students.sort(Comparator.comparing(Student::getDepartment));
				break;
			case "gender":
				students.sort(Comparator.comparing(Student::getGender));
				break;
			case "gpa":
				students.sort(Comparator.comparing(Student::getGpa));
				break;
			case "level":
				students.sort(Comparator.comparing(Student::getLevel));
				break;
			case "address":
				students.sort(Comparator.comparing(Student::getAddress));
				break;
			default:
				break;
		}

		if (order.equals("descending")) {
			Collections.reverse(students);
		}

		return students;
	}

	public boolean isDataChanged(Student oldStudent, String first_name, String last_name, String department,
			String gender, Double gpa, Long level, String address) throws Exception {
		try {
			boolean dataChanged = false;
			String existingFirstName = oldStudent.getFirst_name();
			String existingLastName = oldStudent.getLast_name();
			String existingDepartment = oldStudent.getDepartment();
			String existingGender = oldStudent.getGender();
			Double existingGpa = oldStudent.getGpa();
			Long existingLevel = oldStudent.getLevel();
			String existingAddress = oldStudent.getAddress();

			if (!existingFirstName.equals(first_name) ||
					!existingLastName.equals(last_name) ||
					!existingDepartment.equals(department) ||
					!existingGender.equals(gender) ||
					!existingGpa.equals(gpa) ||
					!existingLevel.equals(level) ||
					!existingAddress.equals(address)) {
				dataChanged = true;
			}
			return dataChanged;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean departmentChanged(Long student_id, String department) {
		boolean departmentChanged = false;

		try {
			Student oldStudent = getStudentByStudentId(student_id);
			String existingDepartment = oldStudent.getDepartment();
			if (!existingDepartment.equals(department)) {
				departmentChanged = true;
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return departmentChanged;
	}

	public boolean isLevelLarge(Long level) {
		try {
			return level >= 3;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean areFieldsString(String... fields) {
		for (String field : fields) {
			try {
				String fieldWithoutSpaces = field.replaceAll("\\s+", "");
				if (!(fieldWithoutSpaces.matches("^[a-zA-Z]+$"))) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	public boolean isGPAValid(Double gpa) {
		try {
			return gpa >= 0 && gpa <= 4;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean areFieldsValid(String... fields) {
		for (String field : fields) {
			try {
				if (field == null || field.trim().isEmpty()) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	public boolean areFieldsValidNumeric(String... fields) {
		for (String field : fields) {
			try {
				Long value = Long.parseLong(field);
				if (value == null || value <= 0) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	public void validateField(String fieldName, String value, BindingResult bindingResult) {
		if (!areFieldsValid(value)) {
			bindingResult.addError(
					new FieldError("student", fieldName, "There is an Empty Field. Try again!"));
		} else {
			switch (fieldName) {
				case "student_id":
					try {
						long studentIdValue = Long.parseLong(value);
						if (areFieldsValidNumeric(value)) {
							Student studentResult = getStudentByStudentId(studentIdValue);
							if (studentResult != null) {
								bindingResult.addError(new FieldError("student", "student_id",
										"Student id already exists. Please choose a different one."));
							}
						} else {
							bindingResult.addError(
									new FieldError("student", fieldName,
											"Should be a numeric value and greater than 0. Try again!"));
						}
					} catch (NumberFormatException e) {
						bindingResult.addError(
								new FieldError("student", fieldName,
										"Should be a numeric value and greater than 0. Try again!"));
					}
					break;
				case "level":
					try {
						Long.parseLong(value);
						if (!areFieldsValidNumeric(value)) {
							bindingResult.addError(
									new FieldError("student", fieldName,
											"Should be a numeric value and greater than 0. Try again!"));
						}
					} catch (NumberFormatException e) {
						bindingResult.addError(new FieldError("student", fieldName,
								"Should be a numeric value and greater than 0. Try again!"));
					}
					break;
				case "gpa":
					try {
						double gpaValue = Double.parseDouble(value);
						if (!isGPAValid(gpaValue)) {
							bindingResult.addError(
									new FieldError("student", fieldName,
											"Should be a numeric value and between 0 and 4. Try again!"));
						}
					} catch (NumberFormatException e) {
						bindingResult.addError(
								new FieldError("student", fieldName,
										"Should be a numeric value and between 0 and 4. Try again!"));
					}
					break;
				case "first_name":
				case "last_name":
				case "department":
				case "address":
					try {
						if (!areFieldsString(value)) {
							bindingResult
									.addError(new FieldError("student", fieldName,
											"First name, last name, department and address should be letters or words. Try again!"));
						}
					} catch (NumberFormatException e) {
						bindingResult.addError(
								new FieldError("student", fieldName,
										"First name, last name, department and address should be letters or words. Try again!"));
					}
					break;
				default:
					break;
			}
		}
	}

}
