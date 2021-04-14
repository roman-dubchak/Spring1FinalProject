package com.gb.hw_lesson12_finalproject.repo;

import com.gb.hw_lesson12_finalproject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
