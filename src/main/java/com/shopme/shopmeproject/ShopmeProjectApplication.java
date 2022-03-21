package com.shopme.shopmeproject;

import com.shopme.admin.user.UserNotFoundException;
import com.shopme.admin.user.UserService;
import com.shopme.common.entity.User;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("ALL")
@SpringBootApplication
@EntityScan({"com.shopme.common.entity", "com.shopme.admin"})
@Configuration
@Service
@Repository
public class ShopmeProjectApplication {
    @Autowired
    public static UserService service;

    public static void main(String[] args) throws IOException, UserNotFoundException {
        User user = new User();
        User user1 = service.get(73);
        System.out.println(user1);
        File copied = new File(
                "/Users/decagon/Desktop/ShopmeProject/ShopmeWebParent/ShopmeBackEnd/src/main/resources/static");
        File original = new File("/Users/decagon/Desktop/ShopmeProject/user-photos" + user.getId());
        FileUtils.copyDirectory(original, copied);
        SpringApplication.run(ShopmeProjectApplication.class, args);
    }

}
