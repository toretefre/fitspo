package tdt4140.gr1806.app.core;

import java.sql.Date;

public class DayWithStepsData {

    private Date day;
    private int steps;

    public DayWithStepsData(Date day, int steps) {
        this.day=day;
        this.steps=steps;
    }

    public Date getDay() {
        return day;
    }

    public int getSteps() {
        return steps;
    }

    public String toString() {
        return day.toString();
    }
}
