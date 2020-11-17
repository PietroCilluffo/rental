package com.rentalcar.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;

import com.rentalcar.dao.VehicleDao;

import com.rentalcar.dao.impl.VehicleDaoImpl;
import com.rentalcar.entity.Vehicle;

@WebServlet("/vehicle/add")
public class AddVehicleController extends HttpServlet{
	 private static final long serialVersionUID = 1L; //probabile problema?
	 
	    private VehicleDao vehicleDao = VehicleDaoImpl.getInstance();
	 
	    public AddVehicleController() {
	        // Do Nothing
	    }
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	List<Vehicle> vehicles = vehicleDao.findAllVehicles();
	    	request.setAttribute("vehicleList", vehicles);
	        request.getRequestDispatcher("/ParcoAuto.jsp").forward(request, response);
	    }
	 
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	        String custId = request.getParameter("id");
	        String modello = request.getParameter("modello");
	        String casa = request.getParameter("casa");
	        String anno = request.getParameter("anno");
	        String targa = request.getParameter("targa");
	       
	    
	        Vehicle vehicle = new Vehicle( targa, modello, casa,anno);
	        
	        if (custId == null || custId == "")
	        	vehicleDao.saveVehicle(vehicle);
	        else {
	            int id = Integer.parseInt(custId);
	            vehicle.setId(id);
	            vehicleDao.updateVehicle(vehicle);
	        }
	 
	        response.sendRedirect(request.getContextPath() + "/home");
	    }
}
