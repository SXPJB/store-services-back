package com.fsociety.storeservices.endpoint;

import com.fsociety.storeservices.entity.Torders;
import com.fsociety.storeservices.service.TordersService;
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
@RequestMapping("/torders")
public class TordersEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TordersEndpoint.class);

	@Autowired
	private TordersService tordersService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Torders torders){
		LOGGER.debug(">>>Insert()->torders:{}",torders);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tordersService.insert(torders);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, torders: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tordersService.update(id,data);
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
			tordersService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Torders>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Torders>>> response=null;
		List<Torders>tordersList=null;
		try{
			tordersList=tordersService.findAll(page,size);
			response=Utils.<List<Torders>>response(HttpStatus.OK,"Lista encontrada",tordersList);
		}catch (Exception e){
			response=Utils.<List<Torders>>response(HttpStatus.NOT_FOUND,"Lista encontrada",tordersList);
		}
		return response;
	}
}
