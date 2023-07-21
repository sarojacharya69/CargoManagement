package net.ims.service;

import net.ims.entity.ShippingList;
import net.ims.entity.TrackingInfo;

import net.ims.entity.Users;
import net.ims.exceptionalhandler.RecordNotFoundException;
import net.ims.repo.CargoRepo;
import net.ims.repo.ShippingListRepo;
import net.ims.repo.TrackingInfoRepo;
import net.ims.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TrackingInfoDAOImp implements TrackingInfoDAO {


    @Autowired
    private TrackingInfoRepo trackingInfoRepo;

    public List<TrackingInfo> getAllTrackingList(){

        return trackingInfoRepo.findAll();

    }

    @Override
    public int saveTrackingInfo(TrackingInfo trackingInfo) {
        TrackingInfo trackingInfo1=trackingInfoRepo.save(trackingInfo);
        if(trackingInfo1!=null)
            return 1;
        else
            return 0;
    }

    @Override
    public TrackingInfo saveOrUpdateTrackingInfo(TrackingInfo tr) {
        Optional<TrackingInfo> TrinfoOptional = trackingInfoRepo.findById(tr.getTlid());
        TrackingInfo b=TrinfoOptional.get();
        if (b!=null)
            b=trackingInfoRepo.save(b);

        return b;
    }

    public TrackingInfo addTrackingInfo(TrackingInfo tr)throws RecordNotFoundException{

        TrackingInfo sh= trackingInfoRepo.save(tr);
        if (sh!=null)
            return sh;
        else
            throw new RecordNotFoundException("Record Not Found");

    }

    public boolean deleteTracking(Integer id)  {
        Optional<TrackingInfo> userOptional =trackingInfoRepo.findById(id);
        TrackingInfo trrr=userOptional.get();
        boolean flag =false;
        if (trrr!=null) {
            trackingInfoRepo.deleteById(id);
            flag=true;
        } else {


        }return flag;
    }

    public TrackingInfo getTTById(int id){

        Optional<TrackingInfo>ttOptional=trackingInfoRepo.findById(id);
        if(ttOptional.isPresent())
            return ttOptional.get();
        else return null;
    }
    public TrackingInfo findByTrNumber(String TrNumber)throws RecordNotFoundException
    {
        TrackingInfo tt = trackingInfoRepo.findByTrNumber(TrNumber);

        if (tt!=null)
            return tt;
        else
            throw new RecordNotFoundException("User Not Found");


    }


}
