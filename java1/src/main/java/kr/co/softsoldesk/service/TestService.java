package kr.co.softsoldesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.DAO.TestDAO;

@Service
public class TestService {
	
	@Autowired
	TestDAO testDao;
	
	public String testMethod() {
		
		String str = testDao.testMethod();
		
		return "str";
	}
}
