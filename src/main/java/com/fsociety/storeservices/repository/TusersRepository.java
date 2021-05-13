package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Tusers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TusersRepository extends JpaRepository<Tusers,Integer>{
}
