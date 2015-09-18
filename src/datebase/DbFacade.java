package datebase; 

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
}