package com.rentalcar.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rentalcar.entity.User;
import com.rentalcar.entity.Vehicle;
import com.rentalcar.dao.ReservationDao;
import com.rentalcar.dao.impl.ReservationDaoImpl;
import com.rentalcar.dao.VehicleDao;
import com.rentalcar.dao.impl.VehicleDaoImpl;
import com.rentalcar.entity.Reservation;
 
@WebServlet("/reservation/add")
public class AddReservationController extends HttpServlet {
    private static final long serialVersionUID = 1L; 
 
    private ReservationDao reservationDao = ReservationDaoImpl.getInstance();
    private VehicleDao vehicleDao = VehicleDaoImpl.getInstance();
 
    public AddReservationController() {
        // Do Nothing
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List< Vehicle> vehicles = vehicleDao.findAllVehicles();
    	request.setAttribute("vehicleList", vehicles);
  
    	
        request.getRequestDispatcher("/addReservation.jsp").forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 HttpSession currentSession = request.getSession();
        String custId = request.getParameter("id");
       // String idUser = request.getParameter("id_user");
        DateTimeFormatter dateTimeFormatter;
        dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String tempInizio= request.getParameter("dataInizio");
        String tempFine =request.getParameter("dataFine");
        LocalDate dataInizio = null;
        LocalDate dataFine = null;
        dataInizio = dataInizio.parse(String.format(tempInizio, dateTimeFormatter));
        dataFine = dataInizio.parse(String.format(tempFine, dateTimeFormatter));
        
        LocalDate now = LocalDate.now();
       
        String targa = request.getParameter("targa");
        String id = request.getParameter("idReservation");
        System.out.println(id+ "id Reservation");
        if(id == null || id == "") { //sto aggiungendo una prenotazione e sonbo l'user
        	  
          	 int idUserInt = (int)currentSession.getAttribute("id");
          
           //   int idUserInt = Integer.parseInt(idUser);
              User user = new User();
              user.setId(idUserInt);
              
              Vehicle vehicle = new Vehicle();
              vehicle.setTarga(targa);
              Reservation reservation = new Reservation(user,dataInizio, dataFine,vehicle);
              
            
                 reservationDao.saveReservation(reservation);
        }else {     //non sto effettuando un inserimento
        	
        	if(currentSession.getAttribute("email").equals("super@rentalcar")) { //sono l'admin e sto aggiornando la prenotazione
        		int idRes = Integer.parseInt(id);
           	 int idUserInt = reservationDao.findUserById(idRes);
           	
                  
           	 User user = new User();
                user.setId(idUserInt);
                
                Vehicle vehicle = new Vehicle();
                vehicle.setTarga(targa);
              
                Reservation reservation = new Reservation(user,dataInizio, dataFine,vehicle);
                
           	  
           	  reservation.setId(idRes);
           	  reservation.setApprovazione(true);
           	  reservationDao.updateReservation(reservation);
        		
        	}else { //sono l'utente e sto cercando di modificare la prenotazione
        		
        		int idRes = Integer.parseInt(id);
              	 int idUserInt = reservationDao.findUserById(idRes);
              	
                     
              	 User user = new User();
                   user.setId(idUserInt);
                   
                   Vehicle vehicle = new Vehicle();
                   vehicle.setTarga(targa);
                 
                   Reservation reservation = new Reservation(user,dataInizio, dataFine,vehicle);
                   
              	  
              	  reservation.setId(idRes);
             	  reservationDao.updateReservation(reservation);
        	}
        }
     
      
 
        response.sendRedirect(request.getContextPath() + "/home");
    }
 
}
