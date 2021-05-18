package com.fsociety.storeservices.service.impl;

import com.fsociety.storeservices.entity.Torders;
import com.fsociety.storeservices.repository.TordersRepository;
import com.fsociety.storeservices.service.TordersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.Date;

@Service
public class TordersServiceImpl implements TordersService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TordersServiceImpl.class);

	@Autowired
	private TordersRepository tordersRepository;

	@Override
	public Torders insert(Torders torders ) throws Exception{
		LOGGER.debug(">>>Insert()->torders:{}",torders);
		Torders orders=null;
		try{
			torders.setTotal(0.0);
			torders.setCeratedAt(new Date());
			torders.setDestinationDir("");
			torders.setDelivered(false);
			torders.setStatus(true);
			orders=tordersRepository.save(torders);

		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		return orders;
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, torders: {}",id,data);
		try{
			Optional<Torders> tordersOptional = tordersRepository.findById(id);
			if(!tordersOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//idUser
//			if(data.containsKey("idUser")){
//				Integer idUser = (Integer)data.get("idUser");
//				tordersOptional.get().setIdUser(idUser);
//			}
			//destinationDir
			if(data.containsKey("destinationDir")){
				String destinationDir = data.get("destinationDir").toString();
				tordersOptional.get().setDestinationDir(destinationDir);
			}
			//total
			if(data.containsKey("total")){
				Double total = Double.parseDouble(data.get("total").toString());
				tordersOptional.get().setTotal(total);
			}
			//isDelivered
			if(data.containsKey("isDelivered")){
				Boolean isDelivered = (Boolean)data.get("isDelivered");
				tordersOptional.get().setDelivered(isDelivered);
			}
			if(data.containsKey("state")){
				String state=data.get("state").toString();
				tordersOptional.get().setState(state);
			}
			//createdBy
			if(data.containsKey("createdBy")){
				Integer createdBy = (Integer)data.get("createdBy");
				tordersOptional.get().setCreatedBy(createdBy);
			}
			//ceratedAt
			if(data.containsKey("ceratedAt")){
				Date ceratedAt = (Date)data.get("ceratedAt");
				tordersOptional.get().setCeratedAt(ceratedAt);
			}
			//modifiedAt
			if(data.containsKey("modifiedAt")){
				Date modifiedAt = (Date)data.get("modifiedAt");
				tordersOptional.get().setModifiedAt(modifiedAt);
			}
			//modifiedBy
			if(data.containsKey("modifiedBy")){
				Integer modifiedBy = (Integer)data.get("modifiedBy");
				tordersOptional.get().setModifiedBy(modifiedBy);
			}
			//status
			if(data.containsKey("status")){
				Boolean status = (Boolean)data.get("status");
				tordersOptional.get().setStatus(status);
			}
			tordersRepository.save(tordersOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Torders> tordersOptional = tordersRepository.findById(id);
			if(!tordersOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tordersRepository.delete(tordersOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Torders> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Torders>tordersList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tordersList = tordersRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tordersList: {}",tordersList);
		return tordersList;
	}

}
