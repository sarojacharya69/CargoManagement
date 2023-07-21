package net.ims.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TRACKING_INFO")
public class TrackingInfo {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "TLID")
        private int tlid;
        @Column(name = "SENDER")
        private String senderName;
        @Column(name = "RECIVER")
        private String reciverName;
        @Column(name = "TRACKING_NUM")
        private String trNumber;
        @Column(name = "STATUS")
        private String status;




}
