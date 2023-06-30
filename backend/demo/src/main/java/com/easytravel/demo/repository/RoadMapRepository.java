package com.easytravel.demo.repository;

import com.easytravel.demo.entity.RoadMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadMapRepository extends JpaRepository<RoadMap, Long> { }
