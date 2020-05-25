package com.boushib.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoFactory {
  private String url;
  private String username;
  private String password;

  DaoFactory(String url, String username, String password){
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public static DaoFactory getInstance(){
    try {
      Class.forName("org.postgresql.Driver");
    } catch(Exception e){
      e.printStackTrace();
    }

    return new DaoFactory("jdbc:postgresql://localhost:5432/cars_shop", "postgres", "123456");
  }

  public Connection getConnection() throws Exception {
    return DriverManager.getConnection(url, username, password);
  }

  // get different dao
  // getCarsDao, getUsersDao...
  public CarsDao getCarsDao(){
    return new CarsDaoImpl(this);
  }
}
