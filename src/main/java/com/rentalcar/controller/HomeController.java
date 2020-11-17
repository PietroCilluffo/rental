package com.rentalcar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rentalcar.dao.UserDao;
import com.rentalcar.dao.impl.UserDaoImpl;
import com.rentalcar.entity.User;
import com.rentalcar.entity.Vehicle;
import com.rentalcar.dao.ReservationDao;
import com.rentalcar.dao.impl.ReservationDaoImpl;
import com.rentalcar.entity.Reservation;
@WebServlet( urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private UserDao userDao = UserDaoImpl.getInstance();
    private ReservationDao reservationDao = ReservationDaoImpl.getInstance();
     
    public HomeController() {
        // Do Nothing
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    

    
    	 HttpSession currentSession = request.getSession();
    	if(currentSession.getAttribute("isSuper").equals(0)) {
    		int id = (int)currentSession.getAttribute("id");
    	 	 System.out.println("------------------------------SOno l user con id "+id);
    	    //	List<Reservation> reservations = reservationDao.findReservationById(id);
    	    //	request.setAttribute("reservationList", reservations);
    	     List<Object[]> reservations_ve = reservationDao.findReservationById(id);
             List<Reservation> reservations = new ArrayList<Reservation>();
             List<Vehicle> vehicles = new ArrayList<Vehicle>();
            for(int i = 0; i< reservations_ve.size(); i++) {
         	   Object[] res = ( Object[] )reservations_ve.get(i);
         	   Reservation r = (Reservation)res[0];
                Vehicle v = (Vehicle)res[1];
                reservations.add(r);
                vehicles.add(v);
                
             
            }
            
            request.setAttribute("reservationList", reservations);
	    	request.setAttribute("vehicleList", vehicles);
    	  
    	}else {
    		List<User> users = userDao.findAllUsers();
	    	request.setAttribute("userList", users);
    	}
   
 
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
