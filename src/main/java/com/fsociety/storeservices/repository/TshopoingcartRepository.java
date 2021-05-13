package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Tshopoingcart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TshopoingcartRepository extends JpaRepository<Tshopoingcart,Integer>{
}
