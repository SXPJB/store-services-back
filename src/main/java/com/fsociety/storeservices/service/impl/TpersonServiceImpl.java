package com.fsociety.storeservices.service.impl;

import com.fsociety.storeservices.entity.Tperson;
import com.fsociety.storeservices.repository.TpersonRepository;
import com.fsociety.storeservices.service.TpersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class TpersonServiceImpl implements TpersonService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TpersonServiceImpl.class);

	@Autowired
	private TpersonRepository tpersonRepository;

	@Override
	public void insert(Tperson tperson ) throws Exception{
		LOGGER.debug(">>>Insert()->tperson:{}",tperson);
		try{
			tpersonRepository.save(tperson);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tperson: {}",id,data);
		try{
			Optional<Tperson> tpersonOptional = tpersonRepository.findById(id);
			if(!tpersonOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//name
			if(data.containsKey("name")){
				String name = data.get("name").toString();
				tpersonOptional.get().setName(name);
			}
			//paternalSurname
			if(data.containsKey("paternalSurname")){
				String paternalSurname = data.get("paternalSurname").toString();
				tpersonOptional.get().setPaternalSurname(paternalSurname);
			}
			//maternalSurname
			if(data.containsKey("maternalSurname")){
				String maternalSurname = data.get("maternalSurname").toString();
				tpersonOptional.get().setMaternalSurname(maternalSurname);
			}
			//rfc
			if(data.containsKey("rfc")){
				String rfc = data.get("rfc").toString();
				tpersonOptional.get().setRfc(rfc);
			}
			//status
			if(data.containsKey("status")){
				Boolean status = (Boolean)data.get("status");
				tpersonOptional.get().setStatus(status);
			}
			tpersonRepository.save(tpersonOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Tperson> tpersonOptional = tpersonRepository.findById(id);
			if(!tpersonOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tpersonRepository.delete(tpersonOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tperson> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tperson>tpersonList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tpersonList = tpersonRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tpersonList: {}",tpersonList);
		return tpersonList;
	}

}
