package com.fsociety.storeservices.service;
import com.fsociety.storeservices.entity.Troles;
import java.util.List;
import java.util.Map;
public interface TrolesService{
	void insert(Troles troles) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Troles> findAll(int page,int size) throws Exception;
}
