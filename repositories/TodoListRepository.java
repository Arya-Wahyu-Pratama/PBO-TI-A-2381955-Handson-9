package repositories;

import entities.TodoList;
public interface TodoListRepository {
    TodoList[] getAll();
    void add(TodoList TodoList);
    boolean remove(int id);
    boolean edit(TodoList TodoList);
}