package com.shopme.admin;

import com.shopme.admin.user.UserRepository;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUserWithOneRow(){
        Role roleAdmin = entityManager.find(Role.class, 1);
        User user = new User("popsi1@gmail.com","1234", "obum", "stev");
        user.addRole(roleAdmin);
        User userSaved = repo.save(user);
        assertThat(userSaved.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateUserWithTwoRow(){
        User user = new User("ste2@gmail.com","1234", "obum", "stev");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);
        user.addRole(roleAssistant);
        user.addRole(roleEditor);
        User userSaved = repo.save(user);
        assertThat(userSaved.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){
        Iterable<User> users = repo.findAll();
        users.forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void testGetUserById(){
        User user = repo.findById(1).get();
        System.out.println(user);
        assertThat(user).isNotNull();

    }

    @Test
    public void testUpdateUserDetails(){
        User user = repo.findById(1).get();
        user.setEnabled(true);
        user.setEmail("pop@gmail.com");
        repo.save(user);

    }

    @Test
    public void testUpdateUserRoles(){
        User user = repo.findById(6).get();
        Role roleEditor = new Role(1);
        Role roleSales = new Role(5);

        user.getRoles().remove(new Role(5));
        //user.getRoles().add(roleSales);
        repo.save(user);
    }

    @Test
    public void testDeleteUser(){
        repo.deleteById(10);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "steven@gmail.com";
        User user=repo.getUserByEmail(email);
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    public void testCountById(){
        Integer id = 1;
        Long countById=repo.countById(id);
        assertThat(countById).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testDisableUser(){
        Integer id =1;
        repo.updateEnabledStatus(17,false);
    }

    @Test
    public void testEnableUser(){
        Integer id =1;
        repo.updateEnabledStatus(17,true);
    }
}
