package com.shopme.admin;

import com.shopme.admin.user.UserRepository;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.nio.file.Files;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTest {

    // this test is to encode password before encoding the database password

    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "stev123";
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
        boolean matches = bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
        assertThat(matches).isTrue();
    }

//    @Test // this test is use to copy file or directory to another file or directory
//    public void givenCommonsIoAPI_whenCopied_thenCopyExistsWithSameContents()
//            throws IOException {
//
//        File copied = new File(
//                "/Users/decagon/Desktop/ShopmeProject/ShopmeWebParent/ShopmeBackEnd/src/main/resources/static");
//        File original = new File("/Users/decagon/Desktop/ShopmeProject/user-photos54/obum pics.jpg");
//        FileUtils.copyFileToDirectory(original, copied);
//
//        assertThat(copied).exists();  //this test will pass
//        assertThat(Files.readAllLines(original.toPath())   // this test will fail
//                .equals(Files.readAllLines(copied.toPath())));
 //   }
}

// this test is to encode the password in the database

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class PasswordEncoderTest {
//    @Autowired
//    UserRepository userRepository;
//    @Test
//    public void testEncodePassword(){
//        User user1= userRepository.findById(1).get();
//        System.out.println(user1);
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String rawPassword = "stev123";
//        String encodedPassword = bCryptPasswordEncoder.encode(user1.getPassword());
//        System.out.println(encodedPassword);
//        boolean matches = bCryptPasswordEncoder.matches(user1.getPassword(), encodedPassword);
//        assertThat(matches).isTrue();
//    }
//}