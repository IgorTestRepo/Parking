/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author jesus-my-lord
 */
public class Auto {
    
    private final String number;
    private final String model;
    private final String startTime;
    
    private String endTime;
    private int payment;

    public Auto(String number, String model, String startTime) {
        this.number = number;
        this.model = model;
        this.startTime = startTime;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getPayment() {
        return payment;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
    
    
    
    
}
