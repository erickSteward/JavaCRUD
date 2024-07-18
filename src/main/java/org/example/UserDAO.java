package org.example;
import java.security.PrivilegedAction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    //Method for creating a new user
    public void addUser(User user){
        String sql = "INSERT INTO users (name, email) VALUES (? , ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //A method to get all the users from the db
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try(Connection conn = DataBaseConnection.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
                users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    //A method to find a single user by there id
    public User getUserById(int id){
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        try(Connection conn = DataBaseConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return  user;
    }

    //A method to Update a User
    public void updateUser(User user){
        String sql = "Update users SET name = ?, email = ? WHERE id = ?";

        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //A method to delete a user by id
    public void deleteUser(int id){
        String sql = " DELETE FROM users WHERE id = ?";

        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
