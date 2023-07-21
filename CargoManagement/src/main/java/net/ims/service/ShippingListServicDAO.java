package net.ims.service;

import net.ims.entity.ShippingList;
import net.ims.entity.Users;
import net.ims.exceptionalhandler.RecordNotFoundException;

import java.util.List;

public interface ShippingListServicDAO  {


        public ShippingList getShippingByID(int id);
        public ShippingList saveCargo(ShippingList shippingList);
        public ShippingList SaveOrUpdateShippingList(ShippingList shippingList);
        public boolean deleteShippingList(int id);
        public List<ShippingList> getShippingListByID(int id);
        List<ShippingList> getAllShippingList();
        ShippingList findByTrackingNum(String trackingNum)throws RecordNotFoundException;
        List<ShippingList> findBySenderName(String SenderName);
    }

