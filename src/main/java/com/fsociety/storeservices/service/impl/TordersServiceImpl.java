package com.fsociety.storeservices.service.impl;

import com.fsociety.storeservices.entity.Torders;
import com.fsociety.storeservices.entity.Tshopoingcart;
import com.fsociety.storeservices.entity.bo.OrderFullBuilder;
import com.fsociety.storeservices.entity.bo.OrdersFullBO;
import com.fsociety.storeservices.entity.bo.ShoppingBuilder;
import com.fsociety.storeservices.entity.bo.ShoppingCartBO;
import com.fsociety.storeservices.repository.TordersRepository;
import com.fsociety.storeservices.repository.TshopoingcartRepository;
import com.fsociety.storeservices.service.TordersService;
import com.fsociety.storeservices.service.TshopoingcartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class TordersServiceImpl implements TordersService {


    private static final Logger LOGGER = LoggerFactory.getLogger(TordersServiceImpl.class);

    @Autowired
    private TordersRepository tordersRepository;

    @Autowired
    private TshopoingcartRepository tshopoingcartRepository;

    @Override
    public Torders insert(Torders torders) throws Exception {
        LOGGER.debug(">>>Insert()->torders:{}", torders);
        Torders orders = null;
        try {
            torders.setTotal(0.0);
            torders.setCeratedAt(new Date());
            torders.setDestinationDir("");
            torders.setDelivered(false);
            torders.setStatus(true);
            orders = tordersRepository.save(torders);

        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
        return orders;
    }

    @Override
    public void update(Integer id, Map<String, Object> data) throws Exception {

        LOGGER.debug(">>>> update->id: {}, torders: {}", id, data);
        try {
            Optional<Torders> tordersOptional = tordersRepository.findById(id);
            if (!tordersOptional.isPresent()) {
                throw new Exception("No existe el registro");
            }

            //destinationDir
            if (data.containsKey("destinationDir")) {
                String destinationDir = data.get("destinationDir").toString();
                tordersOptional.get().setDestinationDir(destinationDir);
            }
            //total
            if (data.containsKey("total")) {
                Double total = Double.parseDouble(data.get("total").toString());
                tordersOptional.get().setTotal(total);
            }
            //isDelivered
            if (data.containsKey("isDelivered")) {
                Boolean isDelivered = (Boolean) data.get("isDelivered");
                tordersOptional.get().setDelivered(isDelivered);
            }
            if (data.containsKey("state")) {
                String state = data.get("state").toString();
                tordersOptional.get().setState(state);
            }
            //createdBy
            if (data.containsKey("createdBy")) {
                Integer createdBy = (Integer) data.get("createdBy");
                tordersOptional.get().setCreatedBy(createdBy);
            }
            //ceratedAt
            if (data.containsKey("ceratedAt")) {
                Date ceratedAt = (Date) data.get("ceratedAt");
                tordersOptional.get().setCeratedAt(ceratedAt);
            }
            //modifiedAt
            if (data.containsKey("modifiedAt")) {
                Date modifiedAt = (Date) data.get("modifiedAt");
                tordersOptional.get().setModifiedAt(modifiedAt);
            }
            //modifiedBy
            if (data.containsKey("modifiedBy")) {
                Integer modifiedBy = (Integer) data.get("modifiedBy");
                tordersOptional.get().setModifiedBy(modifiedBy);
            }
            //status
            if (data.containsKey("status")) {
                Boolean status = (Boolean) data.get("status");
                tordersOptional.get().setStatus(status);
            }
            tordersRepository.save(tordersOptional.get());
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        LOGGER.debug(">>>> delete->id: {}", id);
        try {
            Optional<Torders> tordersOptional = tordersRepository.findById(id);
            if (!tordersOptional.isPresent()) {
                throw new Exception("No existe el registro");
            }
            tordersRepository.delete(tordersOptional.get());
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
    }

    @Override
    public List<Torders> findAll(int page, int size) throws Exception {
        LOGGER.debug(">>>> findAll <<<< page: {} size: {}", page, size);
        List<Torders> tordersList = null;
        try {
            Pageable pageable = PageRequest.of(page, size);
            tordersList = tordersRepository.findAll(pageable).toList();
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
        LOGGER.debug(">>>> findAll <<<< tordersList: {}", tordersList);
        return tordersList;
    }

    @Override
    public List<OrdersFullBO> findOrderByUserPage(int idUser) throws Exception {
        List<OrdersFullBO> ordersFullBOS = null;
        try {
            List<Torders> torders = tordersRepository.findOrderByUserPage(idUser);
            if (torders.isEmpty()) {
                throw new Exception("No existe datos de ordenes");
            }
            ordersFullBOS = new ArrayList<>();
            List<OrdersFullBO> finalOrdersFullBOS = ordersFullBOS;
            torders.forEach(o -> {
                try {
                    finalOrdersFullBOS.add(findShoppingCartByUser(o));
                } catch (Exception e) {
                    LOGGER.error("ocurrio un error");
                }
            });
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e.getMessage());
            throw new Exception(e);
        }
        return ordersFullBOS;
    }

    private OrdersFullBO findShoppingCartByUser(Torders torders) throws Exception {
        OrdersFullBO ordersFullBO = null;
        List<ShoppingCartBO> shoppingCartBOS;
        try {
            shoppingCartBOS = new ArrayList<>();
            List<Tshopoingcart> tshopoingcarts = tshopoingcartRepository.findShoppingCartByOrder(torders.getId());
            tshopoingcarts.forEach(s -> {
                ShoppingCartBO bo = ShoppingBuilder.fromEntity(s);
                shoppingCartBOS.add(bo);
            });
            ordersFullBO = OrderFullBuilder.getOrderFull(torders, shoppingCartBOS);
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e.getMessage());
            throw new Exception(e);
        }
        return ordersFullBO;
    }

}
