package test; 

import datebase.DbFacade;
import entity.Customers;
import entity.Employees;
import entity.Offices;

public class Tester { 
    public static void main(String[] args) {
        DbFacade dbFacade = new DbFacade();
        
        //Employees newEmploye = dbFacade.createEmploye("Peter", "Petersen", "x58776", "Peter@peter.dk", "CEO");
        
        
        Customers customer = new Customers(103);
        customer.setCustomerName("Peter Hansen");
        Customers updatedCustomer = dbFacade.updateCustomer(customer);
        
    }
}