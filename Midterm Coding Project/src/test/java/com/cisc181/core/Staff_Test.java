package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import com.cisc181.core.Staff;
import com.cisc181.eNums.eTitle;

import com.cisc181.Exception.PersonException;

public class Staff_Test {

	@BeforeClass
	public static void setup() throws PersonException {
	}
	
	
	@Test
	public void test() {
		
		Staff John = new Staff(eTitle.MR, 1000.00);
		Staff Mary = new Staff(eTitle.MRS, 2000.00);
		Staff Tom = new Staff(eTitle.MR, 3000.00);
		Staff Chris = new Staff(eTitle.MR, 4000.00);
		Staff Jane = new Staff(eTitle.MS, 5000.00);
		
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		staffs.add(John);
		staffs.add(Mary);
		staffs.add(Tom);
		staffs.add(Chris);
		staffs.add(Jane);
		
		double TotalSalary = 0;
		double ExpectedAverage = (1000+2000+3000+4000+5000) / 5;
		double AverageSalary;
		for (Staff x : staffs) {
			TotalSalary += x.getSalary();
		}
		AverageSalary = TotalSalary / staffs.size();
		
		assertEquals(ExpectedAverage, AverageSalary, 0.01);
	}	

	@Test (expected = PersonException.class)
	public void PersonExceptionTest1() throws PersonException {
		Staff James = new Staff (eTitle.MR, "1(302)3007146");
	}
	
	@Test (expected = PersonException.class)
	public void PersonExpceptionTest2() throws PersonException {
		Staff Dwight = new Staff (eTitle.MR);
		Dwight.setDOB(new Date(12,2,3));
	}
}
