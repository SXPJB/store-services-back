package com.fsociety.storeservices.service.impl;

import com.fsociety.storeservices.entity.Tproducts;
import com.fsociety.storeservices.repository.TproductsRepository;
import com.fsociety.storeservices.service.TproductsService;
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
public class TproductsServiceImpl implements TproductsService {


    private static final Logger LOGGER = LoggerFactory.getLogger(TproductsServiceImpl.class);

    @Autowired
    private TproductsRepository tproductsRepository;

    @Override
    public void insert(Tproducts tproducts) throws Exception {
        LOGGER.debug(">>>Insert()->tproducts:{}", tproducts);
        try {
            tproducts.setCeratedAt(new Date());
            tproducts.setStatus(true);
            tproductsRepository.save(tproducts);
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
    }

    @Override
    public void update(Integer id, Map<String, Object> data) throws Exception {

        LOGGER.debug(">>>> update->id: {}, tproducts: {}", id, data);
        try {
            Optional<Tproducts> tproductsOptional = tproductsRepository.findById(id);
            if (!tproductsOptional.isPresent()) {
                throw new Exception("No existe el registro");
            }
            //title
            if (data.containsKey("title")) {
                String title = data.get("title").toString();
                tproductsOptional.get().setTitle(title);
            }
            //description
            if (data.containsKey("description")) {
                String description = data.get("description").toString();
                tproductsOptional.get().setDescription(description);
            }
            //price
            if (data.containsKey("price")) {
                Double price = Double.parseDouble(data.get("price").toString());
                tproductsOptional.get().setPrice(price);
            }
            //image1
            if (data.containsKey("image1")) {
                String image1 = data.get("image1").toString();
                tproductsOptional.get().setImage1(image1);
            }
            //inventary
            if (data.containsKey("inventary")) {
                Integer inventary = (Integer) data.get("inventary");
                tproductsOptional.get().setInventary(inventary);
            }
            tproductsOptional.get().setCreatedBy((Integer) data.get("createdBy"));
            tproductsOptional.get().setModifiedAt(new Date());
            tproductsRepository.save(tproductsOptional.get());
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        LOGGER.debug(">>>> delete->id: {}", id);
        try {
            Optional<Tproducts> tproductsOptional = tproductsRepository.findById(id);
            if (!tproductsOptional.isPresent()) {
                throw new Exception("No existe el registro");
            }
            tproductsRepository.delete(tproductsOptional.get());
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
    }

    @Override
    public List<Tproducts> findAll(int page, int size) throws Exception {
        LOGGER.debug(">>>> findAll <<<< page: {} size: {}", page, size);
        List<Tproducts> tproductsList = null;
        try {
            Pageable pageable = PageRequest.of(page, size);
            tproductsList = tproductsRepository.findAll(pageable).toList();
        } catch (Exception e) {
            LOGGER.error("Exception: {}", e);
            throw new Exception(e);
        }
        LOGGER.debug(">>>> findAll <<<< tproductsList: {}", tproductsList);
        return tproductsList;
    }

}
