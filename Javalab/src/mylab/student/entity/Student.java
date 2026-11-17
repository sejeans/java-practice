package mylab.student.entity;

import mylab.student.exception.InvalidGradeException;

public class Student {
    private String studentId;   // 학번
    private String name;        // 이름
    private String major;       // 전공
    private int grade;          // 학년

    public Student() { }

    public Student(String studentId, String name, String major, int grade) throws InvalidGradeException {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        setGrade(grade);        // this.grade = grade 대신 setter를 거쳐서 검사도 같이 받음
    }

    public void setGrade(int grade) throws InvalidGradeException {
        if (grade < 1 || grade > 4) {
            throw new InvalidGradeException("학년은 1~4 사이의 값이어야 합니다.");
        }
        this.grade = grade;     // 통과했을 때만 저장
    }

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

    public int getGrade() {
        return grade;
    }

}
