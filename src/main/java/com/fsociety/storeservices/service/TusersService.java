package com.fsociety.storeservices.service;
import com.fsociety.storeservices.entity.Tusers;
import java.util.List;
import java.util.Map;
public interface TusersService{
	void insert(Tusers tusers) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tusers> findAll(int page,int size) throws Exception;
}
