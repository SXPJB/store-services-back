package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Ttraking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TtrakingRepository extends JpaRepository<Ttraking,Integer>{
    List<Ttraking>findByOrder(@Param("idOrder") int idOrder);
}
