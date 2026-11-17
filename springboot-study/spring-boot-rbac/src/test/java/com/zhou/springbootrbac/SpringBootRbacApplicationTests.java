package com.zhou.springbootrbac;

import com.zhou.springbootrbac.eneity.RoleEntity;
import com.zhou.springbootrbac.eneity.UserEntity;
import com.zhou.springbootrbac.repository.AuthRepository;
import com.zhou.springbootrbac.repository.RoleRepository;
import com.zhou.springbootrbac.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringBootRbacApplicationTests {
    @Autowired
    RoleRepository roleRepository;

    @Test
    void contextLoads() {
        Integer i=new Integer(0);
        Integer j=new Integer(0);
        System.out.println(i==j);
        System.out.println(j.equals(i));
    }
    @Autowired
    private  UserRepository userRepository;
    @Test
    void testUserRespotory(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("张三");
        userEntity.setPassword("123456");
        userRepository.insert(userEntity);
    }
    @Resource(name ="bCryptPasswordEncoder" )
    private PasswordEncoder passwordEncoder;
    @Test
    void testPasswordEncoder(){

    }

    @Test
    void test1Encoder(){
        System.out.println(roleRepository.selectListByUserId(1L));
    };
}
