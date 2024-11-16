package services;

import entities.TodoList;

public interface TodoListService {
    TodoList[] getTodoList();

    void addTodoList(String todo);
    boolean removeList(int number);
    boolean editTodoList(int number, String todo);

    boolean removeTodoList(Integer integer);
}