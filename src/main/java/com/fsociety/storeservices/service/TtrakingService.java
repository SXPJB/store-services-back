package com.fsociety.storeservices.service;

import com.fsociety.storeservices.entity.Ttraking;
import java.util.List;
import java.util.Map;
public interface TtrakingService{
	void insert(Ttraking ttraking) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Ttraking> findAll(int page,int size) throws Exception;
}

