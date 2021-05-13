package com.fsociety.storeservices.service.impl;

import com.fsociety.storeservices.entity.Troles;
import com.fsociety.storeservices.repository.TrolesRepository;
import com.fsociety.storeservices.service.TrolesService;
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
public class TrolesServiceImpl implements TrolesService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TrolesServiceImpl.class);

	@Autowired
	private TrolesRepository trolesRepository;

	@Override
	public void insert(Troles troles ) throws Exception{
		LOGGER.debug(">>>Insert()->troles:{}",troles);
		try{
			trolesRepository.save(troles);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, troles: {}",id,data);
		try{
			Optional<Troles> trolesOptional = trolesRepository.findById(id);
			if(!trolesOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//description
			if(data.containsKey("description")){
				String description = data.get("description").toString();
				trolesOptional.get().setDescription(description);
			}
			//status
			if(data.containsKey("status")){
				Integer status = (Integer)data.get("status");
				trolesOptional.get().setStatus(status);
			}
			trolesRepository.save(trolesOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Troles> trolesOptional = trolesRepository.findById(id);
			if(!trolesOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			trolesRepository.delete(trolesOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Troles> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Troles>trolesList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			trolesList = trolesRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< trolesList: {}",trolesList);
		return trolesList;
	}

}
