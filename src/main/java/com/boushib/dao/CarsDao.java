package com.boushib.dao;
import com.boushib.beans.*;

import java.util.List;

public interface CarsDao {
  void insertCar( Car car );
  void updateCar( Car car );
  void deleteCar(int id);
  Car getCarBydId(int id);
  List<Car> getCars();
}
