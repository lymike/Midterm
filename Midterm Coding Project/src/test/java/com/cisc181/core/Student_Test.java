package com.cisc181.core;
/*
 * I put everything in one testing method because for some reason when I try 
 * to create a different testing method, it just skips through it without testing
 * it.  
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.Exception.PersonException;
import com.cisc181.eNums.eMajor;

public class Student_Test {

	static ArrayList<Course> courses = new ArrayList<Course>();
	static ArrayList<Semester> semesters = new ArrayList<Semester>();
	static ArrayList<Section> sections = new ArrayList<Section>();
	
	static Course CISC181 = new Course(UUID.randomUUID(), "Intro to Computer Science II", 3);
	static Course MATH242 = new Course(UUID.randomUUID(), "Calculus BC", 4);
	static Course PHYS207 = new Course(UUID.randomUUID(), "Fundamental of Physics I", 4);
	
	static Semester Fall = new Semester(UUID.randomUUID(), new Date(116, 9, 1), new Date(116, 12, 15));
	static Semester Spring = new Semester(UUID.randomUUID(), new Date(117, 2, 6), new Date(117, 5, 30));
	
	static Section CompSci1 = new Section(CISC181.getCourseID(), Fall.getSemesterID(), UUID.randomUUID(), 117);
	static Section CompSci2 = new Section(CISC181.getCourseID(), Spring.getSemesterID(), UUID.randomUUID(), 205);
	static Section Math1 = new Section(MATH242.getCourseID(), Fall.getSemesterID(), UUID.randomUUID(), 323);
	static Section Math2 = new Section(MATH242.getCourseID(), Spring.getSemesterID(), UUID.randomUUID(), 323);
	static Section Phys1 = new Section(PHYS207.getCourseID(), Fall.getSemesterID(), UUID.randomUUID(), 141);
	static Section Phys2 = new Section(PHYS207.getCourseID(), Spring.getSemesterID(), UUID.randomUUID(), 288);
	
	@BeforeClass
	public static void setup() {
		
		courses.add(CISC181);
		courses.add(MATH242);
		courses.add(PHYS207);
		
		semesters.add(Fall);
		semesters.add(Spring);
		
		sections.add(CompSci1);
		sections.add(CompSci2);
		sections.add(Math1);
		sections.add(Math2);
		sections.add(Phys1);
		sections.add(Phys2);
		
	}

	@Test
	public void TestGPA() throws PersonException {
		
		Student Mike = new Student(eMajor.BUSINESS);
		Student Jim = new Student(eMajor.PHYSICS);
		Student Ryan = new Student(eMajor.CHEM);
		Student Pam = new Student(eMajor.COMPSI);
		Student Scott = new Student(eMajor.NURSING);
		Student Logan = new Student(eMajor.PHYSICS);
		Student Tim = new Student(eMajor.COMPSI);
		Student Jean = new Student(eMajor.PHYSICS);
		Student Kim = new Student(eMajor.BUSINESS);
		Student Frank = new Student(eMajor.NURSING);
		
		ArrayList<Student> students = new ArrayList<Student>();
		
		students.add(Mike);
		students.add(Jim);
		students.add(Ryan);
		students.add(Pam);
		students.add(Scott);
		students.add(Logan);
		students.add(Tim);
		students.add(Jean);
		students.add(Kim);
		students.add(Frank);
		
		ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
		for (Section x : sections) {
			for (Student y : students) {
				enrollments.add(new Enrollment(x.getSectionID(), y.getStudentID()));
			}
		}
		
		for (Enrollment e : enrollments) {
			if(e.getStudentID() == Mike.getStudentID()) {
				e.setGrade(4.0);
			}
			else if (e.getStudentID() == Jim.getStudentID()) {
				e.setGrade(3.9);
			}
			else if (e.getStudentID() == Ryan.getStudentID()) {
				e.setGrade(3.8);
			}
			else if (e.getStudentID() == Pam.getStudentID()) {
				e.setGrade(3.7);
			}
			else if (e.getStudentID() == Scott.getStudentID()) {
				e.setGrade(3.6);
			}
			else if (e.getStudentID() == Logan.getStudentID()) {
				e.setGrade(3.5);
			}
			else if (e.getStudentID() == Tim.getStudentID()) {
				e.setGrade(3.4);
			}
			else if (e.getStudentID() == Jean.getStudentID()) {
				e.setGrade(3.3);
			}
			else if (e.getStudentID() == Kim.getStudentID()) {
				e.setGrade(3.2);
			}
			else if (e.getStudentID() == Frank.getStudentID()) {
				e.setGrade(3.1);
			}
		}
		
		int TotalCred = 0;
		for (Section section : sections) {
			if (section.getCourseID() == CISC181.getCourseID()) {
				TotalCred += CISC181.getGradePoints();
			}
			else if (section.getCourseID() == MATH242.getCourseID()) {
				TotalCred += MATH242.getGradePoints();
			}
			else {
				TotalCred += PHYS207.getGradePoints();
			}
		}
		
		// Average GPA Testing
		double MikeGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Mike.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					MikeGPA += CISC181.getGradePoints()*e.getGrade();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					MikeGPA += MATH242.getGradePoints()*e.getGrade();
				}
				else {
					MikeGPA += PHYS207.getGradePoints()*e.getGrade();
				}
			}
		}
		MikeGPA /= TotalCred;
		assertEquals(MikeGPA, 4, 0.1);
		
		double JimGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Jim.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					JimGPA += e.getGrade()*CISC181.getGradePoints();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					JimGPA += e.getGrade()*MATH242.getGradePoints();
				}
				else {
					JimGPA += e.getGrade()*PHYS207.getGradePoints();
				}
			}
		}
		JimGPA /= TotalCred;
		assertEquals(JimGPA, 3.9, 0.1);
		
		double RyanGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Ryan.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					RyanGPA += e.getGrade()*CISC181.getGradePoints();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					RyanGPA += e.getGrade()*MATH242.getGradePoints();
				}
				else {
					RyanGPA += e.getGrade()*PHYS207.getGradePoints();
				}
			}
		}
		RyanGPA /= TotalCred;
		assertEquals(RyanGPA, 3.8, 0.1);
		
		double PamGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Pam.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					PamGPA += e.getGrade()*CISC181.getGradePoints();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					PamGPA += e.getGrade()*MATH242.getGradePoints();
				}
				else {
					PamGPA += e.getGrade()*PHYS207.getGradePoints();
				}
			}
		}
		PamGPA /= TotalCred;
		assertEquals(PamGPA, 3.7, 0.1);
		
		double ScottGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Scott.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					ScottGPA += e.getGrade()*CISC181.getGradePoints();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					ScottGPA += e.getGrade()*MATH242.getGradePoints();
				}
				else {
					ScottGPA += e.getGrade()*PHYS207.getGradePoints();
				}
			}
		}
		ScottGPA /= TotalCred;
		assertEquals(ScottGPA, 3.6, 0.1);
		
		double LoganGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Logan.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					LoganGPA += e.getGrade()*CISC181.getGradePoints();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					LoganGPA += e.getGrade()*MATH242.getGradePoints();
				}
				else {
					LoganGPA += e.getGrade()*PHYS207.getGradePoints();
				}
			}
		}
		LoganGPA /= TotalCred;
		assertEquals(LoganGPA, 3.5, 0.1);
		
		double TimGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Tim.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					TimGPA += e.getGrade()*CISC181.getGradePoints();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					TimGPA += e.getGrade()*MATH242.getGradePoints();
				}
				else {
					TimGPA += e.getGrade()*PHYS207.getGradePoints();
				}
			}
		}
		TimGPA /= TotalCred;
		assertEquals(TimGPA, 3.4, 0.1);
		
		double JeanGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Jean.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					JeanGPA += e.getGrade()*CISC181.getGradePoints();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					JeanGPA += e.getGrade()*MATH242.getGradePoints();
				}
				else {
					JeanGPA += e.getGrade()*PHYS207.getGradePoints();
				}
			}
		}
		JeanGPA /= TotalCred;
		assertEquals(JeanGPA, 3.3, 0.1);
		
		double KimGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Kim.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					KimGPA += e.getGrade()*CISC181.getGradePoints();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					KimGPA += e.getGrade()*MATH242.getGradePoints();
				}
				else {
					KimGPA += e.getGrade()*PHYS207.getGradePoints();
				}
			}
		}
		KimGPA /= TotalCred;
		assertEquals(KimGPA, 3.2, 0.1);
		
		double FrankGPA = 0;
		for (Enrollment e : enrollments) {
			if (e.getStudentID() == Frank.getStudentID()) {
				if (e.getSectionID() == CompSci1.getSectionID() || 
						e.getSectionID() == CompSci2.getSectionID()) {
					FrankGPA += e.getGrade()*CISC181.getGradePoints();
				}
				else if (e.getSectionID() == Math1.getSectionID() || 
						e.getSectionID() == Math2.getSectionID()) {
					FrankGPA += e.getGrade()*MATH242.getGradePoints();
				}
				else {
					FrankGPA += e.getGrade()*PHYS207.getGradePoints();
				}
			}
		}
		FrankGPA /= TotalCred;
		assertEquals(FrankGPA, 3.1, 0.1);
		
		// CourseAverage Testing
		double CISCAvg = 0; 
		double MATHAvg = 0;
		double PHYSAvg = 0;
		for(Enrollment e : enrollments){
			if(e.getSectionID() == CompSci1.getSectionID() || 
					e.getSectionID() == CompSci2.getSectionID()){
				CISCAvg += e.getGrade();
			}
			else if(e.getSectionID() == Math1.getSectionID() || 
					e.getSectionID() == Math2.getSectionID()){
				MATHAvg += e.getGrade();
			}
			else{
				PHYSAvg += e.getGrade();
			}
		}
		CISCAvg /= (enrollments.size()/3);
		MATHAvg /= (enrollments.size()/3);
		PHYSAvg /= (enrollments.size()/3);
		
		assertEquals(CISCAvg, 3.55, 0.01);
		assertEquals(MATHAvg, 3.55, 0.01);
		assertEquals(PHYSAvg, 3.55,0.01);
		
		// Changing Major Testing
		Student John = new Student(eMajor.CHEM);
		assertTrue(John.getMajor() == eMajor.CHEM);
		John.setMajor(eMajor.BUSINESS);
	}
	
	
	
	
}