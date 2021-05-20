package com.fsociety.storeservices.endpoint;

import com.fsociety.storeservices.entity.Tshopoingcart;
import com.fsociety.storeservices.entity.bo.OrdersFullBO;
import com.fsociety.storeservices.service.TshopoingcartService;
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
@RequestMapping("/tshopoingcart")
public class TshopoingcartEndpoint {


    private static final Logger LOGGER = LoggerFactory.getLogger(TshopoingcartEndpoint.class);

    @Autowired
    private TshopoingcartService tshopoingcartService;


    @PostMapping("/insert")
    public ResponseEntity<ResponseBody<Void>> insert(@RequestBody Tshopoingcart tshopoingcart) {
        LOGGER.debug(">>>Insert()->tshopoingcart:{}", tshopoingcart);
        ResponseEntity<ResponseBody<Void>> response = null;
        try {
            tshopoingcartService.insert(tshopoingcart);
            response = Utils.<Void>response(HttpStatus.CREATED, "Se inserto el registro", null);
        } catch (Exception e) {
            response = Utils.<Void>response(HttpStatus.BAD_REQUEST, false, "No se puedo insertar el registro", null);
        }
        return response;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseBody<Void>> update(@PathVariable Integer id, @RequestBody Map<String, Object> data) {
        LOGGER.debug(">>>> update->id: {}, tshopoingcart: {}", id, data);
        ResponseEntity<ResponseBody<Void>> response = null;
        try {
            tshopoingcartService.update(id, data);
            response = Utils.<Void>response(HttpStatus.OK, "Se actualizo el registro", null);
        } catch (Exception e) {
            response = Utils.<Void>response(HttpStatus.BAD_REQUEST, false, "No se puedo actualizar el registro", null);
        }
        return response;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<Void>> delete(@PathVariable Integer id) {
        LOGGER.debug(">>>> delete->id: {}", id);
        ResponseEntity<ResponseBody<Void>> response = null;
        try {
            tshopoingcartService.delete(id);
            response = Utils.<Void>response(HttpStatus.OK, "Se elimino el registro", null);
        } catch (Exception e) {
            response = Utils.<Void>response(HttpStatus.BAD_REQUEST, false, "No se puedo eliminar el registro", null);
        }
        return response;
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseBody<List<Tshopoingcart>>> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        LOGGER.debug(">>>> findAll <<<< page: {} size: {}", page, size);
        ResponseEntity<ResponseBody<List<Tshopoingcart>>> response = null;
        List<Tshopoingcart> tshopoingcartList = null;
        try {
            tshopoingcartList = tshopoingcartService.findAll(page, size);
            response = Utils.<List<Tshopoingcart>>response(HttpStatus.OK, "Lista encontrada", tshopoingcartList);
        } catch (Exception e) {
            response = Utils.<List<Tshopoingcart>>response(HttpStatus.NOT_FOUND, "Lista encontrada", tshopoingcartList);
        }
        return response;
    }

    @GetMapping("/findShoppingCartByUserSateC")
    public ResponseEntity<ResponseBody<OrdersFullBO>> findShoppingCartByUserSateC(@RequestParam("idOrder") int idOrder,
                                                                                        @RequestParam("idUser") int idUser) {
        ResponseEntity<ResponseBody<OrdersFullBO>> res = null;
        OrdersFullBO ordersFullBO=null;
        try {
            ordersFullBO = tshopoingcartService.findShoppingCartByUserSateC(idOrder,idUser);
            res = Utils.<OrdersFullBO>response(HttpStatus.OK, "Se encontro el carrito de compras", ordersFullBO);
        } catch (Exception e) {
            res = Utils.<OrdersFullBO>response(HttpStatus.NOT_FOUND, "No existen resultados de la busqueda", ordersFullBO);
        }
        return res;
    }
}
