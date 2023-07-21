package net.ims.service;

import net.ims.entity.Cargo;
import net.ims.entity.ShippingList;


import net.ims.entity.Users;
import net.ims.exceptionalhandler.RecordNotFoundException;
import net.ims.repo.CargoRepo;
import net.ims.repo.ShippingListRepo;
import net.ims.repo.TrackingInfoRepo;
import net.ims.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ShippingListServiceDAOImp implements ShippingListServicDAO {
    @Autowired
   private CargoRepo cargoRepo;
    @Autowired
    private ShippingListRepo shippingListRepo;
    @Autowired
    private TrackingInfoRepo trackingInfoRepo;
    @Autowired
    private  UserRepo userRepo;
    public String generateUniqueCode() {
        Random random = new Random();
        int min = 10000; // Minimum 5-digit number
        int max = 99999; // Maximum 5-digit number
        int randomNumber = random.nextInt(max - min + 1) + min;
        return String.valueOf(randomNumber);
    }




    @Override
    public ShippingList saveCargo(ShippingList shippingList) {

        ShippingList sh =shippingListRepo.save(shippingList);
        if(sh!=null)
                return sh;
            else
                return null;

    }

    @Override
    public ShippingList SaveOrUpdateShippingList(ShippingList shippingList) {


        Optional<ShippingList> userOptional = shippingListRepo.findById(shippingList.getSlid());
        ShippingList sl= userOptional.get();
        if (sl!=null)
            sl=shippingListRepo.save(sl);

        return sl;
    }

    @Override
    public boolean deleteShippingList(int id) {
        Optional<ShippingList> userOptional =shippingListRepo.findById(id);
        ShippingList sh=userOptional.get();
        boolean flag =false;
        if (sh!=null) {
            shippingListRepo.deleteById(id);
            flag=true;
        } else {


        }return flag;
    }




    @Override
    public List<ShippingList> getShippingListByID(int id) {
       // List<ShippingList>list=shippingListRepo.findById(id).get();
        return null;
    }

    @Override
    public ShippingList getShippingByID(int id) {

            return shippingListRepo.findById(id).get();
        }





    @Override
    public List<ShippingList> getAllShippingList() {
        List<ShippingList>shALL=shippingListRepo.findAll();
        return shALL;
    }

    @Override
    public ShippingList findByTrackingNum(String trackingNum) throws RecordNotFoundException {

    ShippingList list = shippingListRepo.findByTrackingNum(trackingNum);

	    if (list!=null)
            return list;
	     else
                 throw new RecordNotFoundException("Record Not Found");


}

    @Override
    public List<ShippingList> findBySenderName(String SenderName) {
        List<ShippingList> ulist = shippingListRepo.findBySenderName(SenderName);

        if (ulist!=null)
            return ulist;
        else
            throw null;

    }




}
