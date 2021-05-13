package com.fsociety.storeservices.service.impl;

import com.fsociety.storeservices.entity.Tshopoingcart;
import com.fsociety.storeservices.repository.TshopoingcartRepository;
import com.fsociety.storeservices.service.TshopoingcartService;
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
public class TshopoingcartServiceImpl implements TshopoingcartService{


	private static final Logger LOGGER = LoggerFactory.getLogger(TshopoingcartServiceImpl.class);

	@Autowired
	private TshopoingcartRepository tshopoingcartRepository;

	@Override
	public void insert(Tshopoingcart tshopoingcart ) throws Exception{
		LOGGER.debug(">>>Insert()->tshopoingcart:{}",tshopoingcart);
		try{
			tshopoingcartRepository.save(tshopoingcart);
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void update(Integer id, Map<String,Object> data) throws Exception{

		LOGGER.debug(">>>> update->id: {}, tshopoingcart: {}",id,data);
		try{
			Optional<Tshopoingcart> tshopoingcartOptional = tshopoingcartRepository.findById(id);
			if(!tshopoingcartOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			//idOrder
			if(data.containsKey("idOrder")){
				Integer idOrder = (Integer)data.get("idOrder");
				tshopoingcartOptional.get().setIdOrder(idOrder);
			}
			//idProduct
			if(data.containsKey("idProduct")){
				Integer idProduct = (Integer)data.get("idProduct");
				tshopoingcartOptional.get().setIdProduct(idProduct);
			}
			//amount
			if(data.containsKey("amount")){
				Integer amount = (Integer)data.get("amount");
				tshopoingcartOptional.get().setAmount(amount);
			}
			//status
			if(data.containsKey("status")){
				Boolean status = (Boolean)data.get("status");
				tshopoingcartOptional.get().setStatus(status);
			}
			tshopoingcartRepository.save(tshopoingcartOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public void delete(Integer id) throws Exception{
		LOGGER.debug(">>>> delete->id: {}",id);
		try{
			Optional<Tshopoingcart> tshopoingcartOptional = tshopoingcartRepository.findById(id);
			if(!tshopoingcartOptional.isPresent()){
				throw new Exception("No existe el registro");
			}
			tshopoingcartRepository.delete(tshopoingcartOptional.get());
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
	}
	@Override
	public List<Tshopoingcart> findAll(int page,int size) throws Exception{
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		List<Tshopoingcart>tshopoingcartList=null;
		try{
			Pageable pageable= PageRequest.of(page,size);
			tshopoingcartList = tshopoingcartRepository.findAll(pageable).toList();
		}catch (Exception e){
			LOGGER.error("Exception: {}",e);
			throw new Exception(e);
		}
		LOGGER.debug(">>>> findAll <<<< tshopoingcartList: {}",tshopoingcartList);
		return tshopoingcartList;
	}

}
