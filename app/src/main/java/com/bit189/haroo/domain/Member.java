package com.bit189.haroo.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Member {
  private int no;
  private String email;
  private String password;
  private String name;
  private String nickname;
  private String profilePicture;
  private String phone;  
  private boolean sex;
  private Date birthDate;
  private String zipcode;
  private String address;
  private String detatilAddress;
  private Date registeredDate;
  private Time registeredTime;
  private String rank;
  private boolean state;
  private ArrayList<Tutor> following;
  private ArrayList<ServiceInfo> myHaroo;
  private ArrayList<ServiceInfo> wishlist;
  private ArrayList<ServiceInfo> myBasket;
  private ArrayList<Question> myQuestion;
  private ArrayList<Review> myReview;
}

