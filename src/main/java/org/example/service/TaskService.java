package org.example.service;

import org.example.dao.TaskDAO;
import org.example.domain.Status;
import org.example.domain.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static java.util.Objects.isNull;

@Service
@EnableTransactionManagement
public class TaskService {
    private TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> getAll(int offset, int limit) {
        return taskDAO.getAll(offset, limit);
    }

    public int getCount() {
        return taskDAO.getCount();
    }

    @Transactional
    public Task editTask(int id, String description, Status status) {
        Task task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Task not exist");
        } else {
            task.setDescription(description);
            task.setStatus(status);
            taskDAO.create(task);
            return task;
        }
    }

    @Transactional
    public Task createTask(String description, Status status) {
        Task task = new Task();
        task.setStatus(status);
        task.setDescription(description);
        taskDAO.create(task);
        return task;
    }

    @Transactional
    public void deleteTask(int id) {
        Task task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Task not exist");
        } else {
            taskDAO.deleteTask(task);
        }
    }
}
