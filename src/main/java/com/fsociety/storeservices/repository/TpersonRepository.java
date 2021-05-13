package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Tperson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TpersonRepository extends JpaRepository<Tperson,Integer>{
}
