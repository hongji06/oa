package oa.process;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class ProcessDefinitionTest {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	/**部署流程定义(classpath)*/
	@Test
	public void deploymentProcessDefinition() {
		Deployment deploy = processEngine.getRepositoryService()
				 						 .createDeployment()
				 						 .name("流程定义")				 
				 						 .addClasspathResource("diagrams/helloworld.bpmn")
				 						 .addClasspathResource("diagrams/helloworld.png")
				 						 .deploy();
		
		System.out.println("部署ID："+deploy.getId());
		System.out.println("部署名称："+deploy.getName());
	}
	
	/**部署流程定义(zip)*/
	@Test
	public void deploymentProcessDefinitionZip() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/helloworld.zip") ;
		ZipInputStream zipInputStream = new ZipInputStream(in);
		
		Deployment deploy = processEngine.getRepositoryService()
										 .createDeployment()
										 .name("流程定义")
										 .addZipInputStream(zipInputStream)
										 .deploy();
		
		System.out.println("Zip部署ID："+deploy.getId());
		System.out.println("Zip部署名称："+deploy.getName());
	}
}
