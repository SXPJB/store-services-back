package com.fsociety.storeservices.service.impl;

import com.fsociety.storeservices.entity.Ttraking;
import com.fsociety.storeservices.entity.bo.TrackingBo;
import com.fsociety.storeservices.entity.bo.TrackingBuilder;
import com.fsociety.storeservices.repository.TtrakingRepository;
import com.fsociety.storeservices.service.TtrakingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class TtrakingServiceImpl implements TtrakingService {


    private static final Logger LOGGER = LoggerFactory.getLogger(TtrakingServiceImpl.class);

    @Autowired
    private TtrakingRepository ttrakingRepository;

    @Override
    public void insert(Ttraking ttraking) throws Exception {
        LOGGER.debug(">>>Insert()->ttraking:{}", ttraking);
        try {
            ttraking.setCeratedAt(new Date());
            ttraking.setStatus(true);
            ttrakingRepository.save(ttraking);
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e.getMessage());
            throw new Exception(e);
        }
    }

    @Override
    public void update(Integer id, Map<String, Object> data) throws Exception {

        LOGGER.debug(">>>> update->id: {}, ttraking: {}", id, data);
        try {
            Optional<Ttraking> ttrakingOptional = ttrakingRepository.findById(id);
            if (!ttrakingOptional.isPresent()) {
                throw new Exception("No existe el registro");
            }
            //direction
            if (data.containsKey("direction")) {
                String direction = data.get("direction").toString();
                ttrakingOptional.get().setDirection(direction);
            }
            //createdBy
            if (data.containsKey("createdBy")) {
                Integer createdBy = (Integer) data.get("createdBy");
                ttrakingOptional.get().setCreatedBy(createdBy);
            }
            //ceratedAt
            if (data.containsKey("ceratedAt")) {
                Date ceratedAt = (Date) data.get("ceratedAt");
                ttrakingOptional.get().setCeratedAt(ceratedAt);
            }
            //status
            if (data.containsKey("status")) {
                Boolean status = (Boolean) data.get("status");
                ttrakingOptional.get().setStatus(status);
            }
            ttrakingRepository.save(ttrakingOptional.get());
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e.getMessage());
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        LOGGER.debug(">>>> delete->id: {}", id);
        try {
            Optional<Ttraking> ttrakingOptional = ttrakingRepository.findById(id);
            if (!ttrakingOptional.isPresent()) {
                throw new Exception("No existe el registro");
            }
            ttrakingRepository.delete(ttrakingOptional.get());
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e.getMessage());
            throw new Exception(e);
        }
    }

    @Override
    public List<Ttraking> findAll(int page, int size) throws Exception {
        LOGGER.debug(">>>> findAll <<<< page: {} size: {}", page, size);
        List<Ttraking> ttrakingList = null;
        try {
            Pageable pageable = PageRequest.of(page, size);
            ttrakingList = ttrakingRepository.findAll(pageable).toList();
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e.getMessage());
            throw new Exception(e);
        }
        LOGGER.debug(">>>> findAll <<<< ttrakingList: {}", ttrakingList);
        return ttrakingList;
    }

    @Override
    public List<TrackingBo> findByOrder(int idOrder) throws Exception {
        List<TrackingBo> trackingBos = null;
        try {
            List<Ttraking> ttrakings = ttrakingRepository.findByOrder(idOrder);
            if(ttrakings.isEmpty()){
                throw new Exception("No existen ordenes");
            }

            List<TrackingBo> finalTrackingBos = new ArrayList<>();
            ttrakings.forEach(t->{
                finalTrackingBos.add(TrackingBuilder.fromEntity(t));
            });
            trackingBos = finalTrackingBos;
        }catch (Exception e){
            LOGGER.error("Exception: {}", e.getMessage());
            throw new Exception(e);
        }
        return trackingBos;
    }

}
