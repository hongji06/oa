package oa;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class TestActiviti {

	@Test
	public void createTable() {
		ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		cfg.setJdbcDriver("com.mysql.jdbc.Driver");
		cfg.setJdbcUrl("jdbc:mysql://localhost:3306/oa?characterEncoding=utf-8");
		cfg.setJdbcUsername("root");
		cfg.setJdbcPassword("root");

		cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine processEngine = cfg.buildProcessEngine();
		System.out.println("createTable: "+processEngine);

	}
	
	@Test
	public void createTableConfig() {
		ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		ProcessEngine processEngine = cfg.buildProcessEngine();
		System.out.println("createTableConfig:"+processEngine);
		
	}
}
