package com.tom.dao;

import com.tom.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private static UserDAO userDAO;

    @BeforeAll
    public static void setupClass(){
        userDAO = new UserDAO();
    }

    @Test
    void testAdd(){
        User newUser = new User("tompeo@gmail.com","trancckute","trancckute11");

        User insertedUser = userDAO.create(newUser);

        assertTrue(insertedUser.getUserId() > 0);
    }

    @Test
    void testUpdate(){
        User user = userDAO.getById(2);
        user.setEmail("updated email");
        user.setFullName("updated fullName");
        User updatedUser = userDAO.update(user);
        assertEquals("updated email", updatedUser.getEmail());
        assertEquals("updated fullName", updatedUser.getFullName());
    }

    //@Test
    void testDelete(){
        userDAO.deleteById(3);
        assertTrue(Objects.isNull(userDAO.getById(3)));
    }

    @Test
    void testGetAll(){
        List<User> userList = userDAO.getAll();
        System.out.println(userList);
        assertTrue(userList.size() > 0);
    }

    @Test
    void testGetTotalRecord(){
        Long totalRecord = userDAO.getTotalRecord();
        System.out.println(totalRecord);
        assertTrue(totalRecord > 0L);
    }

    @Test
    void testGetAllWithHQL(){
        List<User> userList = userDAO.getAllWithHQL();
        System.out.println(userList);
        assertTrue(userList.size() > 0);
    }

    @Test
    void testGetTotalRecordWithHQL(){
        Long totalRecord = userDAO.getTotalRecordWithHQL();
        System.out.println(totalRecord);
        assertTrue(totalRecord > 0L);
    }

    @Test
    void testLogin(){
        assertTrue(userDAO.checkLogin("tom@gmail.com", "tom"));
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception{
        System.out.println("Clean up");
    }
}