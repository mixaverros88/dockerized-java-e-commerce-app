package helpers;

import org.apache.log4j.Logger;
import java.util.Date;

/**
 * <h1>Chart</h1>
 * Evaluate the values for the main chart.
 * @author Mike-George Verros
 * @version 1.0
 */

public class Chart {

    final static Logger logger = Logger.getLogger(Chart.class);
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
