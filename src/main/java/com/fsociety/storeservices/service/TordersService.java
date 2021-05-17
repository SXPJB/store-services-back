package com.fsociety.storeservices.service;
import com.fsociety.storeservices.entity.Torders;
import java.util.List;
import java.util.Map;
public interface TordersService{
	Torders insert(Torders torders) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Torders> findAll(int page,int size) throws Exception;
}
