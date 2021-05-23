package com.fsociety.storeservices.entity.bo;

import com.fsociety.storeservices.entity.Ttraking;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TrackingBuilder {

    public static TrackingBo fromEntity(Ttraking orgin){
        TrackingBo destination=new TrackingBo();
        destination.setId(orgin.getId());
        destination.setDirection(orgin.getDirection());
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy  hh:mm",new Locale("es","MX"));
        String strDate = formatter.format(orgin.getCeratedAt());
        destination.setCreatedAt(strDate);
        return destination;
    }
}
