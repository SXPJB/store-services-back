package com.fsociety.storeservices.endpoint;

import com.fsociety.storeservices.entity.Troles;
import com.fsociety.storeservices.service.TrolesService;
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
@RequestMapping("/troles")
public class TrolesEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TrolesEndpoint.class);

	@Autowired
	private TrolesService trolesService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Troles troles){
		LOGGER.debug(">>>Insert()->troles:{}",troles);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			trolesService.insert(troles);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, troles: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			trolesService.update(id,data);
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
			trolesService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Troles>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Troles>>> response=null;
		List<Troles>trolesList=null;
		try{
			trolesList=trolesService.findAll(page,size);
			response=Utils.<List<Troles>>response(HttpStatus.OK,"Lista encontrada",trolesList);
		}catch (Exception e){
			response=Utils.<List<Troles>>response(HttpStatus.NOT_FOUND,"Lista encontrada",trolesList);
		}
		return response;
	}
}
