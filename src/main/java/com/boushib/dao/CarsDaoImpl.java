package com.boushib.dao;

import com.boushib.beans.Car;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarsDaoImpl implements CarsDao{
  private DaoFactory daoFactory;

  CarsDaoImpl(DaoFactory daoFactory){
    this.daoFactory = daoFactory;
  }

  @Override
  public List<Car> getCars() {
    List<Car> cars = new ArrayList<Car>();

    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;

    try {
      connection = daoFactory.getConnection();
      statement = connection.createStatement();
      result = statement.executeQuery("SELECT * FROM cars;");

      while( result.next() ){
        int id = result.getInt("id");
        String make = result.getString("make");
        String model = result.getString("model");
        int year = result.getInt("year");
        int price = result.getInt("price");
        String country = result.getString("country");
        String city = result.getString("city");

        Car car = new Car();

        car.setId(id);
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setPrice(price);
        car.setCountry(country);
        car.setCity(city);

        cars.add(car);
      }

    } catch (Exception e){
      e.printStackTrace();
    }

    return cars;
  }

  @Override
  public Car getCarBydId(int id) {
    Connection connection = null;
    ResultSet result = null;
    Car car = new Car();

    try {
      connection = daoFactory.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cars WHERE id = ?");
      preparedStatement.setInt(1, id);
      result = preparedStatement.executeQuery();

      while (result.next()){
        car.setId(result.getInt("id"));
        car.setMake(result.getString("make"));
        car.setModel(result.getString("model"));
        car.setYear(result.getInt("year"));
        car.setPrice(result.getInt("price"));
        car.setCountry(result.getString("country"));
        car.setCity(result.getString("city"));
      }
    } catch( Exception e){
      e.printStackTrace();
    }

    return car;
  }

  @Override
  public void insertCar(Car car) {
    Connection connection = null;

    System.out.println("Inserting car....");

    try {
      connection = daoFactory.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT into cars (make, model, year, price, country, city) VALUES(?, ?, ?, ?, ?, ?);");

      preparedStatement.setString(1, car.getMake());
      preparedStatement.setString(2, car.getModel());
      preparedStatement.setInt(3, car.getYear());
      preparedStatement.setInt(4, car.getPrice());
      preparedStatement.setString(5, car.getCountry());
      preparedStatement.setString(6, car.getCity());

      preparedStatement.executeUpdate();
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void updateCar(Car car) {
    Connection connection = null;

    try {
      connection = daoFactory.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cars SET make = ?, model = ?, year = ?, price = ?, country = ?, city = ? WHERE id = ?;");
      preparedStatement.setString(1, car.getMake());
      preparedStatement.setString(2, car.getModel());
      preparedStatement.setInt(3, car.getYear());
      preparedStatement.setInt(4, car.getPrice());
      preparedStatement.setString(5, car.getCountry());
      preparedStatement.setString(6, car.getCity());
      preparedStatement.setInt(7, car.getId());

      preparedStatement.executeUpdate();
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void deleteCar(int id) {
    Connection connection = null;

    try {
      connection = daoFactory.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE from cars WHERE id = ?");
      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();
    } catch (Exception e){
      e.printStackTrace();
    }
  }
}
