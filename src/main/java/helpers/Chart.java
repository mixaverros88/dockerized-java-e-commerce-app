/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;


import java.util.Date;


/**
 * @author user
 */

public class Chart {

    private Date dateChart;
    private double sumChart;


    public Chart() {

    }

    public Chart(Date dateChart, double sumChart) {
        this.dateChart = dateChart;
        this.sumChart = sumChart;
    }

    public Date getDateChart() {
        return dateChart;
    }

    public void setDateChart(Date dateChart) {
        this.dateChart = dateChart;
    }

    public double getSumChart() {
        return sumChart;
    }

    public void setSumChart(double sumChart) {
        this.sumChart = sumChart;
    }


}
