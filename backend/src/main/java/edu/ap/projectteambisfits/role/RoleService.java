package edu.ap.projectteambisfits.role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    void deleteRole(Role role);
    Role saveRole(Role role);

}