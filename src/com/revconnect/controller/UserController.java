/*package com.revconnect.controller;

import com.revconnect.model.User;
import com.revconnect.service.IUserService;
import com.revconnect.service.UserServiceImpl;

import java.util.Scanner;

public class UserController {

    private IUserService userService = new UserServiceImpl();
    private Scanner sc = new Scanner(System.in);

    public void register() {

        User user = new User();

        System.out.print("Email: ");
        user.setEmail(sc.nextLine());

        System.out.print("Username: ");
        user.setUsername(sc.nextLine());

        System.out.print("Password: ");
        user.setPassword(sc.nextLine());

        System.out.print("Role (PERSONAL/CREATOR/BUSINESS): ");
        user.setRole(sc.nextLine().toUpperCase());

        userService.register(user);
    }

    public User login() {

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userService.login(username, password);

        if (user != null) {
            System.out.println("✅ Login Successful!");
            System.out.println("Welcome " + user.getUsername()
                    + " (" + user.getRole() + ")");
            return user;
        } else {
            System.out.println("❌ Invalid Credentials");
            return null;
        }
    }
}*/
package com.revconnect.controller;

import com.revconnect.dao.UserDaoImpl;          // ✅ ADD THIS
import com.revconnect.model.User;
import com.revconnect.service.IUserService;
import com.revconnect.service.UserServiceImpl;

import java.util.Scanner;

public class UserController {

    // FIX IS HERE (THIS LINE ONLY)
    private IUserService userService =
            new UserServiceImpl(new UserDaoImpl());

    private Scanner sc = new Scanner(System.in);

    public void register() {

        User user = new User();

        System.out.print("Email: ");
        user.setEmail(sc.nextLine());

        System.out.print("Username: ");
        user.setUsername(sc.nextLine());

        System.out.print("Password: ");
        user.setPassword(sc.nextLine());

        System.out.print("Role (PERSONAL/CREATOR/BUSINESS): ");
        user.setRole(sc.nextLine().toUpperCase());

        userService.register(user);
    }

    public User login() {

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userService.login(username, password);

        if (user != null) {
            System.out.println(" Login Successful!");
            System.out.println("Welcome " + user.getUsername()
                    + " (" + user.getRole() + ")");
            return user;
        } else {
            System.out.println(" Invalid Credentials");
            return null;
        }
    }
}