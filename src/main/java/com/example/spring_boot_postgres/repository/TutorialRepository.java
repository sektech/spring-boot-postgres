package com.example.spring_boot_postgres.repository;

import com.example.spring_boot_postgres.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TutorialRepository extends JpaRepository<Tutorial,Integer> {

    List<Tutorial> findByTitleContaining(String title);
}
