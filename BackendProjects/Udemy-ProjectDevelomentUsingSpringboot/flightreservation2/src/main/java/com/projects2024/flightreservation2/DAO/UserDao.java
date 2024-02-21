package com.projects2024.flightreservation2.DAO;

import com.projects2024.flightreservation2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String username);
}
