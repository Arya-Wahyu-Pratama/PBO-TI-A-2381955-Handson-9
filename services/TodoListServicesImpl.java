package services;

import entities.TodoList;
import repositories.TodoListRepository;

public class TodoListServicesImpl implements TodoListService {
    private final TodoListRepository todoListRepository;

    public TodoListServicesImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public TodoList[] getTodoList() {
        return todoListRepository.getAll();
    }

    @Override
    public void addTodoList(String todo) {
        if (todo.isEmpty() || todo.isBlank()) {
            System.out.println("Masukkan todo daengan benar");
            return;
        }
    }

    @Override
    public boolean removeList(int number) {
        return removeList(number);
    }

    @Override
    public boolean editTodoList(int number, String todo) {
        TodoList todoList = new TodoList();
        todoList.setId(number);
        todoList.setTodo(todo);
        return todoListRepository.edit(todoList);
    }

    @Override
    public boolean removeTodoList(Integer integer) {
        return false;
    }
}