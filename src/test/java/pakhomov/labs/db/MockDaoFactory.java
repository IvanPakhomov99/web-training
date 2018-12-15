package pakhomov.labs.db;

import com.mockobjects.dynamic.Mock;

import pakhomov.labs.db.DaoFactory;
import pakhomov.labs.db.UserDao;


public class MockDaoFactory extends DaoFactory {
	
	private Mock mockUserDao;
	
	public MockDaoFactory() {
		mockUserDao = new Mock(UserDao.class);
	}

	public Mock getMockUserDao() {
		return mockUserDao;
	}
	
	@Override
	public UserDao getUserDao() {
		
		return (UserDao) mockUserDao.proxy();
	}

}
