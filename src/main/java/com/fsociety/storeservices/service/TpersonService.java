package com.fsociety.storeservices.service;
import com.fsociety.storeservices.entity.Tperson;
import java.util.List;
import java.util.Map;
public interface TpersonService{
	void insert(Tperson tperson) throws Exception;
	void update(Integer id, Map<String,Object> data) throws Exception;
	void delete(Integer id) throws Exception;
	List<Tperson> findAll(int page,int size) throws Exception;
}
