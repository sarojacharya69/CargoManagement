package net.ims.service;

import net.ims.entity.Cargo;
import net.ims.entity.ShippingList;
import net.ims.entity.TrackingInfo;
import net.ims.exceptionalhandler.RecordNotFoundException;

import java.util.List;

public interface TrackingInfoDAO {

    List<TrackingInfo> getAllTrackingList();
    public int saveTrackingInfo(TrackingInfo trackingInfo);
    TrackingInfo saveOrUpdateTrackingInfo(TrackingInfo tr)throws RecordNotFoundException;;
    boolean deleteTracking(Integer id);
    TrackingInfo getTTById(int id);
    TrackingInfo findByTrNumber(String TrNumber)throws RecordNotFoundException;

}
