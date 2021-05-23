package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Tproducts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TproductsRepository extends JpaRepository<Tproducts,Integer>{
    Page<Tproducts>findActivePageOrder(Pageable pageable);
}
