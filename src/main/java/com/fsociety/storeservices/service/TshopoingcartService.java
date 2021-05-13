package com.fsociety.storeservices.service;
import com.fsociety.storeservices.entity.Tshopoingcart;
import java.util.List;
import java.util.Map;
public interface TshopoingcartService{
	void insert(Tshopoingcart tshopoingcart) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tshopoingcart> findAll(int page,int size) throws Exception;
}
