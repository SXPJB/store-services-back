package com.fsociety.storeservices.service;
import com.fsociety.storeservices.entity.Tproducts;
import java.util.List;
import java.util.Map;
public interface TproductsService{
	void insert(Tproducts tproducts) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tproducts> findAll(int page,int size) throws Exception;
}
