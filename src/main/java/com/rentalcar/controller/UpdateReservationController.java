package com.rentalcar.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rentalcar.entity.Reservation;
import com.rentalcar.dao.ReservationDao;

import com.rentalcar.dao.impl.ReservationDaoImpl;

/**
 * Servlet implementation class UpdateReservationController
 */
@WebServlet("/reservation/update")
public class UpdateReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReservationDao reservationDao = ReservationDaoImpl.getInstance();  

    public UpdateReservationController() {
     
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // TODO Auto-generated method stub
		 HttpSession currentSession = request.getSession();
		    String tempInizio= request.getParameter("dataInizio");
	        String tempFine =request.getParameter("dataFine");
	        String targa = request.getParameter("targa");
	        String id = request.getParameter("idReservation");
	        LocalDate dataInizio = null;
	        LocalDate dataFine = null;
	        DateTimeFormatter dateTimeFormatter;
	        dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
	        dataInizio = dataInizio.parse(String.format(tempInizio, dateTimeFormatter));
	        dataFine = dataInizio.parse(String.format(tempFine, dateTimeFormatter));
	        LocalDate now = LocalDate.now();
	        if(now.getYear() == dataInizio.getYear()) {
    			if(now.getMonth().equals(dataInizio.getMonth())) {
    				if(now.getDayOfMonth()-dataInizio.getDayOfMonth()<3) {
    					// ci sono meno di due giorni per fare la modifica
    					currentSession.setAttribute("updatable", 0);
    					 request.getRequestDispatcher("/home").forward(request, response);
    				}
    			}
    		}else {
    			if (id == "" || id == null)
    	            request.getRequestDispatcher("/").forward(request, response);
    	        else {
    	       
    	            
    	          
    	 
    	        
    	 		   request.setAttribute("dataInizio",tempInizio);
    	 	       request.setAttribute("dataFine",tempFine);
    	 	       request.setAttribute("targa",targa);
    	 	       request.setAttribute("idReservation",id);
    	       
    	     
    	            request.getRequestDispatcher("/HandleReservation.jsp").forward(request, response);
    		}
	        
	}

}
}
