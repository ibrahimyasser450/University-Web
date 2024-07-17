package University.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	// when i want to add list of students should comment @GeneratedValue....... to
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "STUDENT_ID", nullable = false)
	private long student_id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String first_name;

	@Column(name = "LAST_NAME", nullable = false)
	private String last_name;

	@Column(name = "DEPARTMENT", nullable = false)
	private String department;

	@Column(name = "GENDER", nullable = false)
	private String gender;

	@Column(name = "GPA", nullable = false)
	private double gpa;

	@Column(name = "LEVEL", nullable = false)
	private long level;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	public Student() {

	}

	public Student(long id, long student_id, String first_name, String last_name, String department, String gender,
			double gpa,
			long level, String address) {
		super();
		this.id = id;
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.department = department;
		this.gender = gender;
		this.gpa = gpa;
		this.level = level;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
