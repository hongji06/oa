package oa.hello;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HelloWorld {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	/**
	 * 部署流程定义
	 */
	@Test
	public void deploymentProcessDefinition() {
		Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署相关service
				.createDeployment()// 创建一个部署对象
				.name("第一个流程定义helloworld")// 添加部署名称
				.addClasspathResource("diagrams/helloworld.bpmn")// 从classpath文件加载一个文件
				.addClasspathResource("diagrams/helloworld.png")// 从classpath文件加载一个文件
				.deploy();// 完成部署
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		System.out.println(deployment.getDeploymentTime());
	}

	/**
	 * 启动流程实例
	 */
	@Test
	public void startProcessInstance() {
		String processDefinitionKey = "myProcess";
		ProcessInstance pi = processEngine.getRuntimeService()// 真正执行的流程实例和执行对象相关service
				.startProcessInstanceByKey(processDefinitionKey);// 使用流程定义的key启动流程实例
		System.out.println("流程实例ID:"+pi.getId());
		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());

	}
	
	/**
	 * 查看个人任务
	 */
	@Test
	public void findMyPersonalTask() {
		String assignee = "王五";
		List<Task> list = processEngine.getTaskService()//与正在执行任务相关的service
		.createTaskQuery()//创建任务查询对象
		.taskAssignee(assignee)//指定个人任务查询，指定办理人
		.list();
		
		if(list != null && list.size()>0) {
			for(Task task : list) {
				System.out.println("任务ID："+task.getId());
				System.out.println("任务名称："+task.getName());
				System.out.println("任务办理人："+task.getAssignee());
				System.out.println("流程实例ID："+task.getProcessInstanceId());
				System.out.println("流程定义ID："+task.getProcessDefinitionId());
			}
		}
	}
	
	
	/**
	 * 完成我的任务
	 */
	@Test
	public void completeMyPersonalTask() {
		String taskId = "15002";
		processEngine.getTaskService().complete(taskId);
		System.out.println("finished task："+taskId);
	}
}
