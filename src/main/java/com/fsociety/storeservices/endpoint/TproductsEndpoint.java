package com.fsociety.storeservices.endpoint;

import com.fsociety.storeservices.entity.Tproducts;
import com.fsociety.storeservices.service.TproductsService;
import com.fsociety.storeservices.config.ResponseBody;
import com.fsociety.storeservices.config.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
@RestController
@RequestMapping("/tproducts")
public class TproductsEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TproductsEndpoint.class);

	@Autowired
	private TproductsService tproductsService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Tproducts tproducts){
		LOGGER.debug(">>>Insert()->tproducts:{}",tproducts);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tproductsService.insert(tproducts);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, tproducts: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tproductsService.update(id,data);
			response= Utils.<Void>response(HttpStatus.OK,"Se actualizo el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo actualizar el registro",null);
		}
	return response;
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<ResponseBody<Void>> delete(@PathVariable Integer id){
		LOGGER.debug(">>>> delete->id: {}",id);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tproductsService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Tproducts>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Tproducts>>> response=null;
		List<Tproducts>tproductsList=null;
		try{
			tproductsList=tproductsService.findAll(page,size);
			response=Utils.<List<Tproducts>>response(HttpStatus.OK,"Lista encontrada",tproductsList);
		}catch (Exception e){
			response=Utils.<List<Tproducts>>response(HttpStatus.NOT_FOUND,"Lista encontrada",tproductsList);
		}
		return response;
	}
}
