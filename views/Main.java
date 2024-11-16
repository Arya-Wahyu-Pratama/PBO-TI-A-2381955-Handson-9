package views;

import config.Database;
import entities.TodoList;
import repositories.TodoListRepository;
import repositories.TodoListRepositoryImpl;
import services.TodoListService;
import services.TodoListServicesImpl;

public class Main {
    public static void main(String[] args) {
        Database database = new Database("my_database", "root", "", "localhost","3306");
        database.setup();

        TodoListRepository todoListRepository = new TodoListRepositoryImpl();
        TodoListService todoListService = new TodoListServicesImpl(todoListRepository);
        TodoListView todoListView = new TodoListTerminalView(todoListService);
        todoListView.run();
    }
}