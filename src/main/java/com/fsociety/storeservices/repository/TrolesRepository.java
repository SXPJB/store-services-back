package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Troles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrolesRepository extends JpaRepository<Troles,Integer>{
}
