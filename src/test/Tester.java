package test; 

import datebase.DbFacade;
import entity.Customers;
import java.util.List;

public class Tester { 
    public static void main(String[] args) {
        DbFacade dbFacade = new DbFacade();
        
//        Employees newEmploye = dbFacade.createEmploye("Peter", "Petersen", "x58776", "Peter@peter.dk", "CEO");
        
        
//        Customers customer = new Customers(103);
//        customer.setCustomerName("Peter Hansen");
//        Customers updatedCustomer = dbFacade.updateCustomer(customer);
        
//        System.out.println("Total employees: " + dbFacade.getEmplyeCount());
        
        List<Customers> customersInCity = dbFacade.getCustomerInCity("Barcelona");
        for (Customers customer : customersInCity) {
            System.out.println(customer.getCustomerName());
        }
    }
}