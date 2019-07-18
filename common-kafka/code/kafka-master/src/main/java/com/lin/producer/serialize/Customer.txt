package com.lin.producer.serialize;

/**
 * @author linjh
 * @date 2019/1/13 16:06
 */
public class Customer {
    private int customerID;
    private String customerName;

    public Customer(int ID, String Name) {
        this.customerID = ID;
        this.customerName = Name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                '}';
    }

    public int getID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
