package com.example.todo.services;

import java.util.List;
import java.util.Optional;

import com.example.todo.dto.TaskDto;
import com.example.todo.dto.TasksDto;
import com.example.todo.entities.Task;

public interface TaskService {

	public Task saveTask(Task task);

	public List<Task> getAllTasks();

	public List<TaskDto> getAllTaskDto();

	//14-04-2023 (checking) 11.35 am
	public TaskDto getTaskDtoById(int id);

	public Task updateTask(int id, Task task);

	List<TasksDto> getAllwithDto(String search, Integer pageSize, Integer pageNumber);

	public Optional<Task> getTaskById(int id);

	// 07-04-2023(checking)
	public void deleteById(int id);
}
