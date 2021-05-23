package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Torders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TordersRepository extends JpaRepository<Torders,Integer>{
    Torders findOrderActiveByUserStateC(@Param("idOrder") int idOrder,@Param("idUser") int idUser);
    List<Torders>findOrderByUserPage(@Param("idUser") int idUser);
    Torders findOrderByUserSateC(@Param("idUser") int idUser);
}
