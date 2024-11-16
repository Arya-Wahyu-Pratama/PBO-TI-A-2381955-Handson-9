package repositories;

import config.Database;
import entities.TodoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TodoListRepositoryDblmpl implements TodoListRepository{
    private final Database database;

    public TodoListRepositoryDblmpl(final Database database, Database database1){
        this.database = database1;
    }
    @Override
    public TodoList[] getAll() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FORM todos";
        List<TodoList> todoLists = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                TodoList todoList = new TodoList();
                Integer id = resultSet.getInt(1);
                String todo = resultSet.getString(2);
                todoList.setTodo(todo);
                todoList.setTodo(String.valueOf(id));
                todoLists.add(todoList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return todoLists.toArray(TodoList[]::new);
    }

    @Override
    public void add(TodoList TodoList) {
        Connection connection = database.getConnection();
        String sqlStetament = "INSERT INTO todos(todo) VALUE(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStetament);
            preparedStatement.setString(1, TodoList.getTodo());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Insert successful");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private Integer getDbId(final Integer id) {
        TodoList[] todolists = getAll();
        Long countElement = Arrays.stream(todolists).filter(Objects::nonNull).count();
        if (countElement.intValue() == 0) {
            return null;
        }
        var dbId = todolists[id - 1].getId();
        return dbId;
    }
    @Override
    public boolean remove(int id) {
        String sqlStatement = "DELETE FROM todo WHERE id = ?";
        Connection conn = database.getConnection();
        var dbId = getDbId(id);
        if (dbId == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, dbId);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Delete successful !");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean edit(TodoList TodoList) {
        return false;
    }
}
