package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        //Create a new user
//        User user = new User("Sara Doe", "sara@email.com");
//        userDAO.addUser(user);

        //Real All Users
        //List<User> users = userDAO.getAllUsers();
        //for(User user1 : users){
        //    System.out.println(user1.getId() + ": " + user1.getName() + "_" + user.getEmail());
        //}

        //Read user by id
//        User user2 = userDAO.getUserById(9);
//        if(user2 !=null){
//            System.out.println("User with id 2: " + user2.getName() + "_" + user2.getEmail());
//        }

        //updata a user
//        User user2 = userDAO.getUserById(9);
//        user2.setName("Sara Smith");
//        user2.setEmail("sara.smith@email.com");
//        userDAO.updateUser(user2);

        userDAO.deleteUser(3);
    }
}