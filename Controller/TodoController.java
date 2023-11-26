package com.example.todo.Controller;

import com.example.todo.Entity.Todo;
import com.example.todo.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {
    @Autowired
    TodoRepository todoRepository;

    @RequestMapping({"/", "/todo"})
    public String index(Model model){
        Iterable<Todo> todos = todoRepository.orderByToDo();
        model.addAttribute("todos", todos);
        return "pages/index";
    }
    @RequestMapping({"/todo/active"})
    public String active(Model model){
        Iterable<Todo> todos = todoRepository.findByCompletedAndPostponed();
        model.addAttribute("todos", todos);
        return "pages/active";
    }
    @RequestMapping({"/todo/completed"})
    public String completed(Model model){
        Iterable<Todo> todos = todoRepository.findByCompleted();
        model.addAttribute("todos", todos);
        return "pages/completed";
    }
    @RequestMapping({"/todo/postponed"})
    public String postponed(Model model){
        Iterable<Todo> todos = todoRepository.findByPostponed();
        model.addAttribute("todos", todos);
        return "pages/postponed";
    }


    @RequestMapping({"/todo/delete/{id}"})
    public String delete(@PathVariable Integer id){
        Todo todos = todoRepository.findById(id).get();
        todoRepository.delete(todos);
        return "redirect:/todo";
    }
    @RequestMapping({"/todo/priority/{id}"})
    public String priority(@PathVariable Integer id){
        Todo todos = todoRepository.findById(id).get();
        if (!todos.getIs_completed() || !todos.getIs_postponed()){
            todos.setIs_active(!todos.getIs_active());
        }
        todoRepository.save(todos);
        return "redirect:/todo";
    }
    @RequestMapping({"/todo/completed/{id}"})
    public String completed(@PathVariable Integer id){
        Todo todos = todoRepository.findById(id).get();
        if (!todos.getIs_postponed()){
            todos.setIs_completed(!todos.getIs_completed());
            todos.setIs_active(false);
        }
        todoRepository.save(todos);
        return "redirect:/todo";
    }
    @RequestMapping({"/todo/postponed/{id}"})
    public String postponed(@PathVariable Integer id){
        Todo todos = todoRepository.findById(id).get();
        if (!todos.getIs_completed() || !todos.getIs_active()){
            todos.setIs_postponed(!todos.getIs_postponed());
        }
        todoRepository.save(todos);
        return "redirect:/todo";
    }
    @GetMapping("/new_to_do")
    public String add(){
        return "pages/new_to_do";
    }

    @PostMapping("/new_to_do")
    public String newToDo(@RequestParam String title){
        Todo todos = new Todo();
        todos.setTitle(title);
        todos.setIs_active(false);
        todos.setIs_postponed(false);
        todos.setIs_completed(false);
        todoRepository.save(todos);
        return "redirect:/todo";
    }
}
