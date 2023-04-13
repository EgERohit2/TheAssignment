package com.example.todo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.todo.dto.UserTaskDto;
import com.example.todo.entities.Task;
import com.example.todo.entities.TaskStatus;
import com.example.todo.entities.User;
import com.example.todo.entities.UserTask;

public interface UserTaskService {

	public UserTask saveUserTask(UserTask userTask);

	public List<UserTask> getAllUserTasks();

	public Optional<UserTask> getUserTaskById(int id);

	public UserTask updateUserTask(int id, UserTask userTask);

//	public List<UserTask> findByStatusAndStartDateAndEndDate(List<TaskStatus> status, List<Date> startDate,
//			List<Date> endDate);

	// public List<UserTask> findByStatusAndStartDateAndEndDate(List<TaskStatus>
	// status, Date startDate, Date endDate);

	List<UserTask> findByStatusAndStartDateAndEndDate(List<TaskStatus> status, List<Date> startDate,
			List<Date> endDate);

	public List<UserTaskDto> getAllUserTaskDto();

	// 05-04-2023
//	public List<UserTaskDto> findBySearch(String search);

	// 11-04-2023(checking) update task status 2.20 pm
	public UserTask updateTaskStatus(int id, UserTask userTask);

	List<Object[]> getAllUserTask();

	UserTask getUserTaskByUserAndTask(Integer user, Integer task);

	// 07-04-2023
//	List<Object[]> getAllUserTaskFilter();
	
	//11-04-2023 (checking) 4.30 pm
	public UserTask updateAssignedTaskStatus(int id, UserTask userTask);
	
	public UserTask updateTaskStatusWithHistory(int userId, int taskId, TaskStatus status) throws Exception; 
	 

}
