package com.anaboru.springsecurity.services;

import com.anaboru.springsecurity.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();


    Role findByRole(String name);

    void save(Role role);
}
