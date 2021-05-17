package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Tusers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TusersRepository extends JpaRepository<Tusers,Integer>{
    Tusers findByEmailAndPassword(@Param("email") String email,@Param("password") String password);
}
