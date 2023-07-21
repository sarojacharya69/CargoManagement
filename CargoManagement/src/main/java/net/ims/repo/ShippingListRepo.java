package net.ims.repo;

import net.ims.entity.ShippingList;
import net.ims.entity.Users;
import net.ims.exceptionalhandler.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingListRepo extends JpaRepository <ShippingList,Integer>{
    ShippingList findByTrackingNum(String trackingNum)throws RecordNotFoundException;

    List<ShippingList> findBySenderName(String SenderName);

}
