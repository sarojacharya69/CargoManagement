package net.ims.controller;


import net.ims.entity.Cargo;
import net.ims.entity.ShippingList;

import net.ims.entity.Users;
import net.ims.exceptionalhandler.RecordNotFoundException;
import net.ims.service.CargoServiceDaoImpl;
import net.ims.service.ShippingListServiceDAOImp;

import net.ims.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class ShippingListController {
    @Autowired
    private ShippingListServiceDAOImp serviceShippingList;
    @Autowired
    private UserService serviceUser;
    @Autowired
    private CargoServiceDaoImpl serviceCargo;

    @GetMapping("/getAllShipping")
    public String allShippingList(@NotNull Model model) {
        List<ShippingList>list=serviceShippingList.getAllShippingList();
        model.addAttribute("dsl", list);
        return ("ShippingListDisplay");

    }

    @RequestMapping("/shippingForm")
    public String findBook() {

        return "shippingList";
    }

    @PostMapping("/createShippingLists")
    public String createShippingList(@RequestParam("receiverName") String receiverName,
                                     @RequestParam("receiverEmail") String receiverEmail,
                                     @RequestParam("receiverPhone") long receiverPhone,
                                     @RequestParam("receiverAddress") String receiverAddress,
                                     @RequestParam("UserID") int UserID,
                                     @RequestParam("status") String status,
                                     @RequestParam("CargoID") int CargoID,
                                     @RequestParam("weight") double weight,Model model) throws RecordNotFoundException {

            ShippingList shippingList = new ShippingList();
            shippingList.setReceiverName(receiverName);
            shippingList.setReceiverAddress(receiverAddress);
            shippingList.setReceiverPhone(receiverPhone);
            shippingList.setReceiverEmail(receiverEmail);
            shippingList.setStatus(status);
            String trackingnumber=serviceShippingList.generateUniqueCode();
            shippingList.setTrackingNum(trackingnumber);

           /* Users users = serviceUser.getUserById(UserID);
            if (users == null) {
                //throw new RecordNotFoundException("User not found");
                model.addAttribute("msg", "user not exit");
                return "DashBoard";
            }
            shippingList.setSender_ID(users.getUid());
            shippingList.setSenderName(users.getUname());

            Cargo cargo = serviceCargo.getCargoById(CargoID);
            if (cargo == null) {
                model.addAttribute("msg","cargo not exit");
                return "DashBoard";
            }
            shippingList.setCargo_ID(cargo.getSbid());
              double dd=cargo.getCost();
              shippingList.setWeight(weight*dd);*/
        try {
            Users users = serviceUser.getUserByIdd(UserID);
            if (users == null) {
                model.addAttribute("msg", "user not exit");
                return "DashBoard";
            }
            shippingList.setSender_ID(users.getUid());
            shippingList.setSenderName(users.getUname());

            Cargo cargo = serviceCargo.getCargoById(CargoID);
            if (cargo == null) {
                model.addAttribute("msg", "cargo not exit");
                return "DashBoard";
            }
            shippingList.setCargo_ID(cargo.getSbid());
            double dd=cargo.getCost();
            shippingList.setWeight(weight*dd);
        } catch (RecordNotFoundException e) {
            // Handle the RecordNotFoundException here
            model.addAttribute("msg", "User not found");
            return "DashBoard";
        } catch (Exception e) {
            // Handle any other exceptions here
            model.addAttribute("msg", "An error occurred Enter your valid  ID or cargo service ID:Try Again");
            return "DashBoard";
        }


        ShippingList shippingList1 = serviceShippingList.saveCargo(shippingList);
            if (shippingList1 != null) {
                model.addAttribute("msg", trackingnumber);
                return "TrackingNumberDisplay";
            } else {
                model.addAttribute("msg", "Failed to create cargo");
              return "DashBoard";
              }}
    @RequestMapping("/editShippingList/{slid}")
    public String editshippingList(@PathVariable int slid,Model model) {
        ShippingList shippingList=serviceShippingList.getShippingByID(slid);
        model.addAttribute("pol",shippingList);
        return "updateShippingList.html";
    }
    @RequestMapping("/deleteShippingList/{slid}")
    public String deleteUser(@PathVariable int slid,Model model) {
        boolean count=serviceShippingList.deleteShippingList(slid);
        String msg=" ";
        if(count==true)
            msg="Delete Success";
        else
            msg="Try Later!";

        model.addAttribute("msg",msg);
        return"redirect:/getAllShipping";
    }
    @PostMapping("/saveShippingListByAdmin")
    public  String update(@ModelAttribute ShippingList list,Model model){
        ShippingList count=serviceShippingList.saveCargo(list);
        String msg=" ";
        if(count!=null)
            msg="Update Success";
        else
            msg="Try Later!";

        model.addAttribute("msg",msg);
        return"redirect:/getAllShipping";
    }
    @GetMapping("/findTrackingInfo")
    public String findPassword(@RequestParam("TrackingNum") String TrackingNum,Model model) {
        try {
            ShippingList b = serviceShippingList.findByTrackingNum(TrackingNum);
            model.addAttribute("ship",b);

        } catch (RecordNotFoundException e) {
            model.addAttribute("error", "User not found");
        }
        return "payment";

    }
    @GetMapping("/paymentSuccess")
    public String Customer(@NotNull Model model)
    {
        model.addAttribute("msg","Thanks for payment");
        return("DashBoard");
    }
    @GetMapping("/findTTtBySenderName")
    public String UserShippingList(@RequestParam("senderName") String senderName, Model model) {
        List<ShippingList> sl = serviceShippingList.findBySenderName(senderName);
        if (sl.isEmpty()) {
            model.addAttribute("msg", "User not found");
        } else {
            model.addAttribute("ship", sl);
        }
        return "MyShippingListDisplay";
    }




    @GetMapping("/searchMyShippingList")
    public String MyList()
    {

        return("SearchMyShippingList");
    }

}





