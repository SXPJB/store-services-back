package com.fsociety.storeservices.endpoint;

import com.fsociety.storeservices.entity.Tusers;
import com.fsociety.storeservices.service.TusersService;
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
@RequestMapping("/tusers")
public class TusersEndpoint{


	private static final Logger LOGGER = LoggerFactory.getLogger(TusersEndpoint.class);

	@Autowired
	private TusersService tusersService;


	@PostMapping("/insert")
	public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Tusers tusers){
		LOGGER.debug(">>>Insert()->tusers:{}",tusers);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tusersService.insert(tusers);
			response= Utils.<Void>response(HttpStatus.CREATED,"Se inserto el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo insertar el registro",null);
		}
	return response;
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String,Object> data){
		LOGGER.debug(">>>> update->id: {}, tusers: {}",id,data);
		ResponseEntity<ResponseBody<Void>> response=null;
		try{
			tusersService.update(id,data);
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
			tusersService.delete(id);
			response= Utils.<Void>response(HttpStatus.OK,"Se elimino el registro",null);
		}catch (Exception e){
			response=Utils.<Void>response(HttpStatus.BAD_REQUEST,false,"No se puedo eliminar el registro",null);
		}
	return response;
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseBody<List<Tusers>>> findAll(@RequestParam("page") int page,@RequestParam("size") int size){
		LOGGER.debug(">>>> findAll <<<< page: {} size: {}",page,size);
		ResponseEntity<ResponseBody<List<Tusers>>> response=null;
		List<Tusers>tusersList=null;
		try{
			tusersList=tusersService.findAll(page,size);
			response=Utils.<List<Tusers>>response(HttpStatus.OK,"Lista encontrada",tusersList);
		}catch (Exception e){
			response=Utils.<List<Tusers>>response(HttpStatus.NOT_FOUND,"Lista encontrada",tusersList);
		}
		return response;
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseBody<Tusers>> login(@RequestBody Map<String,String> data){
		ResponseEntity<ResponseBody<Tusers>> res=null;
		try {
			Tusers tusers = tusersService.login(data.get("email"),data.get("password"));
			res = Utils.<Tusers>response(HttpStatus.OK,"Inciciado con exito",tusers);
		}catch (Exception e){
			res = Utils.<Tusers>response(HttpStatus.NOT_FOUND,false,"Datos incorrectos",null);
		}
		return res;
	}

}
