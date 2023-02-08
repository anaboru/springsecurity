package com.anaboru.springsecurity;

import com.anaboru.springsecurity.models.Role;
import com.anaboru.springsecurity.models.User;
import com.anaboru.springsecurity.repositories.RoleRepository;
import com.anaboru.springsecurity.services.RoleService;
import com.anaboru.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

@Component
public class BeforeTheApplicationStarts {

    private RoleService roleService;

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostConstruct
    public void initialization() {
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        Role userRole = createRoleIfNotFound("ROLE_USER");
        Set<Role> setOfAdminRoles = new HashSet<>();
        Collections.addAll(setOfAdminRoles, adminRole, userRole);
        User admin = new User();
        admin.setId(1L);
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRoles(setOfAdminRoles);
        userService.save(admin);

        Set<Role> setOfUserRoles = new HashSet<>();
        Collections.addAll(setOfUserRoles, userRole);
        User user = new User();
        user.setId(2L);
        user.setFirstName("user");
        user.setLastName("user");
        user.setUsername("user");
        user.setPassword("user");
        user.setRoles(setOfUserRoles);
        userService.save(user);


    }

    @Transactional
    public Role createRoleIfNotFound(String name) {
        Role role = roleService.findByRole(name);
        if (role == null) {
            role = new Role(name);
            roleService.save(role);
        }
        return role;
    }
}


