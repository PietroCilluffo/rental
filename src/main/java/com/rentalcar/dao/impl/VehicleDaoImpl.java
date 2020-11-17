package com.rentalcar.dao.impl;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



import com.rentalcar.dao.VehicleDao;

import com.rentalcar.entity.Vehicle;
import com.rentalcar.util.HibernateUtil;
public class VehicleDaoImpl implements VehicleDao{
	private static VehicleDaoImpl vehicleDaoImpl = null;
    
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public int saveVehicle(Vehicle vehicle) {
        Session session = this.sessionFactory.openSession();
        
        Transaction transaction = session.beginTransaction();
       
        int id = (int)session.save(vehicle);
        transaction.commit();
        session.close();
         
        return id;     
    }
    
    
    public void updateVehicle(Vehicle vehicle) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(vehicle);
        transaction.commit();
        session.close();
    }
    
    public void deleteVehicle(int id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Vehicle vehicle = session.get(Vehicle.class, id);
        session.delete(vehicle);
        transaction.commit();
        session.close();
    }
    
    
    public Vehicle findVehicleById(int id) {
        Session session = this.sessionFactory.openSession();
        Vehicle vehicle = session.get(Vehicle.class, id);
        session.close();
         
        return vehicle;
    }
    
    
    
    @SuppressWarnings("unchecked")
    public List<Vehicle> findAllVehicles() {
    
        Session session = this.sessionFactory.openSession();
        List<Vehicle> vehicleList = session.createCriteria(Vehicle.class).list();
      
        session.close();
         
        return vehicleList;
    }
    
    public static VehicleDao getInstance() {
        if(vehicleDaoImpl == null)
        	vehicleDaoImpl = new VehicleDaoImpl();
    
        return vehicleDaoImpl;
    }

 
 
     
}
