package com.example.service;

import com.example.model.Todo;
import com.example.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    // Bug 1: Using static list as cache without proper invalidation
    private static List<Todo> todoCache = new ArrayList<>();

    public List<Todo> getAllTodos() {
        // Bug 2: Incorrect caching implementation
        if (!todoCache.isEmpty()) {
            return todoCache;  // Returns stale data
        }
        todoCache = todoRepository.findAll();
        return todoCache;
    }

    public Todo createTodo(Todo todo) {
        // Bug 3: Not validating input
        return todoRepository.save(todo);  // Could throw DB exception if title/desc is null
    }

    public Optional<Todo> getTodoById(Long id) {
        // Bug 4: Not handling negative IDs
        return todoRepository.findById(id);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        // Bug 5: Not checking for null before updating fields
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());

        // Bug 6: Completed status can never be set back to false
        if (todoDetails.isCompleted()) {
            todo.setCompleted(true);
        }

        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        // Bug 7: No transactional boundary
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        // Bug 8: Silently failing deletion of completed items
        // (Could be leftover from a "completed items should be archived" requirement)
        if (todo.isCompleted()) {
            return;
        }

        todoRepository.delete(todo);
    }
}
