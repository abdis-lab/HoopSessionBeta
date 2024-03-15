package com.abdisalam.hoopsessionbeta.repository;

import com.abdisalam.hoopsessionbeta.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
