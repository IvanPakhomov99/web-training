package pakhomov.labs.web;

import java.util.Properties;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import pakhomov.labs.db.DaoFactory;
import pakhomov.labs.db.MockDaoFactory;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;

	protected Mock getMockUserDao() {
		return mockUserDao;
	}

	protected void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}

	public void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
	}

	public void tearDown() throws Exception {
		getMockUserDao().verify();
		super.tearDown();
	}

}
