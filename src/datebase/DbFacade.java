package datebase; 

import entity.Customers;
import entity.Employees;
import entity.Offices;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DbFacade { 
    private DbConnector dbConnector = DbConnector.Instance();
    
    
    public Employees createEmploye(String firstName, String lastName, String extention, String email, String jobTitle){
        EntityManager em = dbConnector.getEm();
        Employees employe;
        
        try{
            em.getTransaction().begin();
            
            Query query = em.createQuery("SELECT MAX(e.employeeNumber) FROM Employees e");
            int number = (int)query.getSingleResult();
            employe = new Employees(++number, lastName, firstName, extention, email, jobTitle);
            employe.setOfficeCode(new Offices("1"));
            
            em.persist(employe);
            
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
        return employe;
    }
    
    public Customers updateCustomer(Customers cust){
        EntityManager em = dbConnector.getEm();
        
        Customers customer = em.find(Customers.class, cust.getCustomerNumber());
        
        try{
            em.getTransaction().begin();
            
            customer.setCustomerName(cust.getCustomerName()); //Do this for each changed attribute on customer
            
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return cust;
    }
}