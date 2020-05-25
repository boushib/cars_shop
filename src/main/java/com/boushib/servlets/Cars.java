package com.boushib.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.boushib.beans.*;
import com.boushib.dao.CarsDao;
import com.boushib.dao.DaoFactory;

@WebServlet(name = "Cars")
public class Cars extends HttpServlet {

  private CarsDao carsDao;

  public void init() throws ServletException {
    DaoFactory daoFactory = DaoFactory.getInstance();
    this.carsDao = daoFactory.getCarsDao();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getServletPath();

    try {
      switch (path){
        case "/new_car.do":
          this.getServletContext().getRequestDispatcher("new_car.jsp").forward(request, response);
          break;
        case "/insert_car.do":
          insertCar(request, response);
          break;
        case "/update_car_p.do":
          populateCarForUpdate(request, response);
          break;
        case "/update_car.do":
          updateCar(request, response);
          break;
        case "/delete.do":
          deleteCar(request, response);
          break;
        default:
          getCars(request, response);
          break;
      }
    } catch (Exception e){
      e.printStackTrace();
    }
  }
  private void insertCar(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
    Car car = new Car();
    String make = request.getParameter("make");
    String model = request.getParameter("model");
    int year = Integer.parseInt(request.getParameter("year"));
    int price = Integer.parseInt(request.getParameter("price"));
    String country = request.getParameter("country");
    String city = request.getParameter("city");

    car.setMake(make);
    car.setModel(model);
    car.setYear(year);
    car.setPrice(price);
    car.setCountry(country);
    car.setCity(city);

    carsDao.insertCar(car);

    request.setAttribute("cars", carsDao.getCars());
    this.getServletContext().getRequestDispatcher("cars.jsp").forward(request, response);
  }
  private void updateCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Car car = new Car();

    int id = Integer.parseInt(request.getParameter("car_id"));
    String make = request.getParameter("make");
    String model = request.getParameter("model");
    int year = Integer.parseInt(request.getParameter("year"));
    int price = Integer.parseInt(request.getParameter("price"));
    String country = request.getParameter("country");
    String city = request.getParameter("city");

    car.setId(id);
    car.setMake(make);
    car.setModel(model);
    car.setYear(year);
    car.setPrice(price);
    car.setCountry(country);
    car.setCity(city);

    carsDao.updateCar(car);

    request.setAttribute("cars", carsDao.getCars());
    this.getServletContext().getRequestDispatcher("cars.jsp").forward(request, response);
  }
  private void populateCarForUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int car_id = Integer.parseInt(request.getParameter("car_id"));
    Car car = carsDao.getCarBydId(car_id);
    request.setAttribute("car", car);
    this.getServletContext().getRequestDispatcher("update_car.jsp").forward(request, response);
  }
  private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int car_id = Integer.parseInt(request.getParameter("car_id"));
    carsDao.deleteCar(car_id);
    response.sendRedirect("cars");
  }
  private void getCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("cars", carsDao.getCars());
    this.getServletContext().getRequestDispatcher("cars.jsp").forward(request, response);
  }


}
