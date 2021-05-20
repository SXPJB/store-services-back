package com.fsociety.storeservices.repository;
import com.fsociety.storeservices.entity.Tshopoingcart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TshopoingcartRepository extends JpaRepository<Tshopoingcart,Integer>{
    List<Tshopoingcart>findShoppingCartByOrder(@Param("idOrder")int idOrder);
}
