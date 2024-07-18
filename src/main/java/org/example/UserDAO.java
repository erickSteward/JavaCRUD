package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    //Method for creating a new user
    public void addUser(User user){
        String sql = "INSERT INTO users (name, email) VALUES (? , ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.executeUpdate();
            logger.info("User added: {}", user);
        } catch (SQLException e){
            logger.error("Error adding user: {}", user, e);
            throw new DatabaseException("Error adding user:" , e);
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
            logger.info("Fetched all Users");
        } catch (SQLException e){
            logger.error("Error fetching user: {}", e);
            throw new DatabaseException("Error fetching user:" , e);
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
            logger.info("Fetched user with ID: {} " , id);
        } catch (SQLException e){
            logger.error("Error fetching user ID: {}", id);
            throw new DatabaseException("Error fetching users" , e);
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
            logger.info("User Updated: {}", user);
        } catch (SQLException e){
            logger.error("Error updating user: {}", user, e);
            throw new DatabaseException("Error updating user:" , e);
        }
    }

    //A method to delete a user by id
    public void deleteUser(int id){
        String sql = " DELETE FROM users WHERE id = ?";

        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Deleted Users with id: {}", id);
        } catch (SQLException e) {
            logger.error("Error deleting user: {}", e);
            throw new DatabaseException("Error deleting user:" , e);
        }
    }
}
