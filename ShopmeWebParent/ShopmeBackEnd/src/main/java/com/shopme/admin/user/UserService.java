package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public List<Role> listRoles() {
        return (List<Role>) roleRepo.findAll();
    }

    public User save(User user) {
        boolean isUpdatingUser = user.getId() != null;
        if (isUpdatingUser) {
            User existingUser = repo.findById(user.getId()).get();
            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else encodePassword(user);
        } else encodePassword(user);
        encodePassword(user);
        return repo.save(user);
    }

    public void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = repo.getUserByEmail(email);
        if (userByEmail == null) {
            return true;
        }
        boolean isCreatedNew = (id == null);
        if (isCreatedNew) {
            if (userByEmail != null) {
                return false;
            } else if (userByEmail.getId() != id) {
                return false;
            }
        }
        return true;
//        return userByEmail == null;
    }

    public User get(Integer id) throws UserNotFoundException {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("could not find user with Id :" + id);
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = repo.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("could not find user with Id :" + id);
        }
        repo.deleteById(id);
    }

    public void updateUserEnabledStatus(Integer id, boolean enabled) {
        repo.updateEnabledStatus(id, enabled);
    }

    public void givenCommonsIoAPI_whenCopied_thenCopyExistsWithSameContents()
            throws IOException {

        File copied = new File(
                "/Users/decagon/Desktop/ShopmeProject/ShopmeWebParent/ShopmeBackEnd/src/main/resources/static");
        File original = new File("/Users/decagon/Desktop/ShopmeProject/user-photos54");
        FileUtils.copyDirectory(original, copied);

    }
}

