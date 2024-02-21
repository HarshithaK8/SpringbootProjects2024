package com.locationproject.location.DAO;

import com.locationproject.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationDAO extends JpaRepository<Location,Integer> {

    @Query("select type, count(type) from Location group by type")
    List<Object[]> findByTypeAndTypeCount();
}
