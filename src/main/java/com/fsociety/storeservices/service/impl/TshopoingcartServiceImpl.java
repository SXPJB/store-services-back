package com.fsociety.storeservices.service.impl;

import com.fsociety.storeservices.entity.Torders;
import com.fsociety.storeservices.entity.Tshopoingcart;
import com.fsociety.storeservices.entity.bo.OrderFullBuilder;
import com.fsociety.storeservices.entity.bo.OrdersFullBO;
import com.fsociety.storeservices.entity.bo.ShoppingBuilder;
import com.fsociety.storeservices.entity.bo.ShoppingCartBO;
import com.fsociety.storeservices.repository.TordersRepository;
import com.fsociety.storeservices.repository.TshopoingcartRepository;
import com.fsociety.storeservices.service.TshopoingcartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class TshopoingcartServiceImpl implements TshopoingcartService {


    private static final Logger LOGGER = LoggerFactory.getLogger(TshopoingcartServiceImpl.class);

    @Autowired
    private TshopoingcartRepository tshopoingcartRepository;

    @Autowired
    private TordersRepository tordersRepository;

    @Override
    public void insert(Tshopoingcart tshopoingcart) throws Exception {
        LOGGER.debug(">>>Insert()->tshopoingcart:{}", tshopoingcart);
        try {
            tshopoingcart.setStatus(true);
            tshopoingcartRepository.save(tshopoingcart);
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
    }

    @Override
    public void update(Integer id, Map<String, Object> data) throws Exception {

        LOGGER.debug(">>>> update->id: {}, tshopoingcart: {}", id, data);
        try {
            Optional<Tshopoingcart> tshopoingcartOptional = tshopoingcartRepository.findById(id);
            if (!tshopoingcartOptional.isPresent()) {
                throw new Exception("No existe el registro");
            }
//			//idOrder
//			if(data.containsKey("idOrder")){
//				Integer idOrder = (Integer)data.get("idOrder");
//				tshopoingcartOptional.get().setIdOrder(idOrder);
//			}
//			//idProduct
//			if(data.containsKey("idProduct")){
//				Integer idProduct = (Integer)data.get("idProduct");
//				tshopoingcartOptional.get().setIdProduct(idProduct);
//			}
            //amount
            if (data.containsKey("amount")) {
                Integer amount = (Integer) data.get("amount");
                tshopoingcartOptional.get().setAmount(amount);
            }
            //status
            if (data.containsKey("status")) {
                Boolean status = (Boolean) data.get("status");
                tshopoingcartOptional.get().setStatus(status);
            }
            tshopoingcartRepository.save(tshopoingcartOptional.get());
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        LOGGER.debug(">>>> delete->id: {}", id);
        try {
            Optional<Tshopoingcart> tshopoingcartOptional = tshopoingcartRepository.findById(id);
            if (!tshopoingcartOptional.isPresent()) {
                throw new Exception("No existe el registro");
            }
            tshopoingcartRepository.delete(tshopoingcartOptional.get());
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
    }

    @Override
    public List<Tshopoingcart> findAll(int page, int size) throws Exception {
        LOGGER.debug(">>>> findAll <<<< page: {} size: {}", page, size);
        List<Tshopoingcart> tshopoingcartList = null;
        try {
            Pageable pageable = PageRequest.of(page, size);
            tshopoingcartList = tshopoingcartRepository.findAll(pageable).toList();
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
        LOGGER.debug(">>>> findAll <<<< tshopoingcartList: {}", tshopoingcartList);
        return tshopoingcartList;
    }

    @Override
    public OrdersFullBO findShoppingCartByUserSateC(int idOrder, int idUser) throws Exception {
        OrdersFullBO ordersFullBO = null;
        Torders torders;
        List<ShoppingCartBO> shoppingCartBOS;

        try {
            torders = tordersRepository.findOrderActiveByUserStateC(idOrder, idUser);
            if(torders==null){
            	throw new Exception("No existe la order");
			}
            shoppingCartBOS = new ArrayList<>();
            List<Tshopoingcart> tshopoingcarts = tshopoingcartRepository.findShoppingCartByOrder(torders.getId());
            AtomicReference<Double> sum = new AtomicReference<>(0.0);
            tshopoingcarts.forEach(s -> {
                ShoppingCartBO bo = ShoppingBuilder.fromEntity(s);
                shoppingCartBOS.add(bo);
                sum.updateAndGet(v -> v + s.getIdProduct().getPrice() * s.getAmount());
            });
            torders.setTotal(sum.get());
            tordersRepository.save(torders);
            ordersFullBO = OrderFullBuilder.getOrderFull(torders, shoppingCartBOS);
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e.getMessage());
            throw new Exception(e);
        }
        return ordersFullBO;
    }

}
