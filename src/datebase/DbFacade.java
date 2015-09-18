package datebase;

import entity.Customers;
import entity.Employees;
import entity.Offices;
import entity.Orders;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DbFacade {

    private DbConnector dbConnector = DbConnector.Instance();

    public Employees createEmploye(String firstName, String lastName, String extention, String email, String jobTitle) {
        EntityManager em = dbConnector.getEm();
        Employees employe;

        try {
            em.getTransaction().begin();

            Query query = em.createQuery("SELECT MAX(e.employeeNumber) FROM Employees e");
            int number = (int) query.getSingleResult();
            employe = new Employees(++number, lastName, firstName, extention, email, jobTitle);
            employe.setOfficeCode(new Offices("1"));

            em.persist(employe);

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return employe;
    }

    public Customers updateCustomer(Customers cust) {
        EntityManager em = dbConnector.getEm();

        Customers customer = em.find(Customers.class, cust.getCustomerNumber());

        try {
            em.getTransaction().begin();

            customer.setCustomerName(cust.getCustomerName()); //Do this for each changed attribute on customer

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cust;
    }

    public long getEmplyeCount() {
        EntityManager em = dbConnector.getEm();
        long count;
        
        try {
            em.getTransaction().begin();
            
            Query q = em.createQuery("select count(e) from Employees e");
            count = (long)q.getSingleResult();
            
            em.getTransaction().commit();
        } finally {
            em.close();

        }
        return count;
    }
    
    public List<Customers> getCustomerInCity(String city){
        EntityManager em = dbConnector.getEm();
        
        List<Customers> customers;
        
        try{
            em.getTransaction().begin();
            
            Query q = em.createNamedQuery("Customers.findByCity").setParameter("city", city);
            customers = q.getResultList();
            
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
        return customers;
    }
    
    public List<Orders> getOrdersOnHold(){
        EntityManager em = dbConnector.getEm();
        
        List<Orders> orders;
        
        try{
            em.getTransaction().begin();
            
            Query q = em.createNamedQuery("Orders.findByStatus");
            orders = q.setParameter("status", "On Hold").getResultList();
            
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return orders;
    }
}
