package com.projects2024.flightreservation2.DAO;

import com.projects2024.flightreservation2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
