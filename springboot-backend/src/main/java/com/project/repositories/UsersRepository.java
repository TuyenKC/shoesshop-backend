package com.project.repositories;

import com.project.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,String> {
    public Users findByUsername(String userName);
    boolean existsByUsername(String userName);
}
