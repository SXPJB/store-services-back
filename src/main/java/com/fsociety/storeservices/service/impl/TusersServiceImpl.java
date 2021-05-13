package com.fsociety.storeservices.service.impl;

import com.fsociety.storeservices.entity.Tusers;
import com.fsociety.storeservices.repository.TusersRepository;
import com.fsociety.storeservices.service.TusersService;
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
public class TusersServiceImpl implements TusersService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TusersServiceImpl.class);

	@Autowired
	private TusersRepository tusersRepository;

	@Override
	public void insert(Tusers tusers ) throws Exception{
		LOGGER.debug(">>>Insert()->tusers:{}",tusers);
		try{
			tusersRepository.save(tusers);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tusers: {}",id,data);
		try{
			Optional<Tusers> tusersOptional = tusersRepository.findById(id);
			if(!tusersOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//idRol
			if(data.containsKey("idRol")){
				Integer idRol = (Integer)data.get("idRol");
				tusersOptional.get().setIdRol(idRol);
			}
			//idPerson
			if(data.containsKey("idPerson")){
				Integer idPerson = (Integer)data.get("idPerson");
				tusersOptional.get().setIdPerson(idPerson);
			}
			//email
			if(data.containsKey("email")){
				String email = data.get("email").toString();
				tusersOptional.get().setEmail(email);
			}
			//password
			if(data.containsKey("password")){
				String password = data.get("password").toString();
				tusersOptional.get().setPassword(password);
			}
			//status
			if(data.containsKey("status")){
				Boolean status = (Boolean)data.get("status");
				tusersOptional.get().setStatus(status);
			}
			tusersRepository.save(tusersOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Tusers> tusersOptional = tusersRepository.findById(id);
			if(!tusersOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tusersRepository.delete(tusersOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tusers> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tusers>tusersList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tusersList = tusersRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tusersList: {}",tusersList);
		return tusersList;
	}

}
