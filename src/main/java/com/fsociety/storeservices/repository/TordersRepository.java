package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Torders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TordersRepository extends JpaRepository<Torders,Integer>{
}
