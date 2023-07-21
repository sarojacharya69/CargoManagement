package net.ims.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USERS")

public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UID")
	private int uid;
	@Column(name="UNAME")
	private String uname;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="PHONE")
	private long phone;
	@Column(name="EMAIL")
	private String email;
	@Column(name="CITY")
	private String city;
	@Column(name="STATE")
	private String state;
	@Column(name="ZIP")
	private String zip;
	@Column(name="GENDER")
	private String gender;

	@Column(name="PASSWORD")
	private String password;
	@Column(name="CONFIRMPASSWORD")
	private String confirmPassword;
	@Column (name="ROLE_ID")
	private int role_id=2;




}
