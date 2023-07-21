package net.ims.repo;

import net.ims.entity.ShippingList;
import net.ims.entity.TrackingInfo;
import net.ims.exceptionalhandler.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingInfoRepo extends JpaRepository <TrackingInfo,Integer> {
    TrackingInfo findByTrNumber(String TrNumber)throws RecordNotFoundException;
}
