package pl.coderslab.users;

import java.util.Arrays;

public class finAll {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        System.out.println(Arrays.toString(userDao.findAll()));
    }
}
