package com.locationproject.location.DAO;

import com.locationproject.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDAO extends JpaRepository<Location,Integer> {
}
