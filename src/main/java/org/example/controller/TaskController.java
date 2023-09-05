package org.example.controller;

import org.example.domain.Task;
import org.example.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllTasks(Model model,
                              @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){

      List<Task> tasks=  taskService.getAll((page-1)*limit, limit);
      model.addAttribute("current_page", page);
      model.addAttribute("tasks", tasks);
       int totalPages = (int) Math.ceil(1.0 * taskService.getCount()/limit);
       List <Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        model.addAttribute("page_numbers", pageNumbers);
       return "tasks";
    }

    @PostMapping("/{id}")
    public String editTask(Model model,
                         @PathVariable Integer id,
                         @RequestBody TaskInfo taskInfo){
        if(isNull(id) || id<0) {
            throw new RuntimeException("Invalid id");
        }

       Task task = taskService.editTask(id, taskInfo.getDescription(), taskInfo.getStatus());
        return getAllTasks(model,1, 10);
    }

    @PostMapping("/")
    public String addTask(Model model,
                         @RequestBody TaskInfo taskInfo){
        taskService.createTask( taskInfo.getDescription(), taskInfo.getStatus());
        return getAllTasks(model,1, 10);

    }

    @DeleteMapping("/{id}")
    public String deleteTask(Model model,
                             @PathVariable Integer id) {
        if(isNull(id) || id<0) {
            throw new RuntimeException("Invalid id");
        }
        taskService.deleteTask(id);
return getAllTasks(model,1, 10);
    }
}
