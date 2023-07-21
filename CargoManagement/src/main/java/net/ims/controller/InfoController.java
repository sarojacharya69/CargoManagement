package net.ims.controller;

import net.ims.entity.Cargo;
import net.ims.entity.ShippingList;
import net.ims.entity.TrackingInfo;
import net.ims.entity.Users;
import net.ims.exceptionalhandler.RecordNotFoundException;
import net.ims.service.CargoServiceDaoImpl;
import net.ims.service.ShippingListServiceDAOImp;
import net.ims.service.TrackingInfoDAOImp;
import net.ims.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class InfoController {

    @Autowired
    private TrackingInfoDAOImp trackingInfoDAOImp;
    @Autowired
    private ShippingListServiceDAOImp serviceShippingList;
    @Autowired
    private UserService serviceUser;
    @Autowired
    private CargoServiceDaoImpl serviceCargo;

        @GetMapping("/getAllTrackingInfo")
        public String trackingInfo( Model model) {
            List<TrackingInfo> list=trackingInfoDAOImp.getAllTrackingList();
            model.addAttribute("dsl", list);
            return ("TrackingListDisplay");

        }

        @RequestMapping("/TrackingForm")
        public String findBook() {

            return "TrackingInfo";
        }

       @RequestMapping("/createTrackingInfo/{slid}")
        public String createShippingList(@PathVariable int slid, Model model,ShippingList list) {

                list= serviceShippingList.getShippingByID(slid);

                if (list == null) {
                    model.addAttribute("msg", "Try again");
                    return "homeAdmin";
                }
                TrackingInfo trackingInfo = new TrackingInfo();
                trackingInfo.setSenderName(list.getSenderName());
                trackingInfo.setReciverName(list.getReceiverName());
                trackingInfo.setTrNumber(list.getTrackingNum());
                trackingInfo.setStatus(list.getStatus());
                int count=trackingInfoDAOImp.saveTrackingInfo(trackingInfo);

                if (count != 0) {
                    model.addAttribute("msg", "Tracking  Created");
                } else {
                    model.addAttribute("msg", "Failed to create tracking");
                    }
                    return "redirect:/getAllTrackingInfo";
                }


    @PostMapping("/updateTTbyAdmin")
    public  String updateTT(@ModelAttribute TrackingInfo trackingInfo,Model model) {

            int count=trackingInfoDAOImp.saveTrackingInfo(trackingInfo);
            String msg=" ";
            if(count!=0)
                msg="Update Success";
            else
                msg="Try Later!";

            model.addAttribute("msg",msg);
            return"redirect:/getAllTrackingInfo";
    }
    @RequestMapping("/deleteTT/{tlid}")
    public String deleteTT(@PathVariable int tlid) {
        trackingInfoDAOImp.deleteTracking(tlid);
        return "redirect:/getAllTrackingInfo";
    }
        @RequestMapping("/editTT/{tlid}")
        public String editTT(@PathVariable int tlid, @NotNull Model model) {
        TrackingInfo TT=trackingInfoDAOImp.getTTById(tlid);
        model.addAttribute("scat",TT);
        return "updateTrackingList.html";
        }
        @GetMapping("/searchMyTrack&Trick")
         public String MyList()
         {

            return("SearchMyTrackingList");
    }

        @GetMapping("/findTTtByTrNumber")
        public String UserTrackingInfo(@RequestParam("trNumber") String trNumber, Model model) {
            /*Try{
                TrackingInfo tt = trackingInfoDAOImp.findByTrNumber(trNumber);
            if (tt == null) {
                model.addAttribute("msg", "User not found");
            } else {
                model.addAttribute("ship", tt);
            }
            return "MyTrackingListDisplay";
        }*/
         try {
           TrackingInfo tt = trackingInfoDAOImp.findByTrNumber(trNumber);
           model.addAttribute("ship", tt);

           } catch (RecordNotFoundException e) {
             model.addAttribute("msg", "Tracking Info not found");
              }
			return "MyTrackingListDisplay";

          }
    }







