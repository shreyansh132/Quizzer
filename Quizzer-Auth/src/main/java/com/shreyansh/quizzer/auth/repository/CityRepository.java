package com.shreyansh.quizzer.auth.repository;

import com.shreyansh.quizzer.auth.entity.IndiaCities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<IndiaCities,String> {

}
