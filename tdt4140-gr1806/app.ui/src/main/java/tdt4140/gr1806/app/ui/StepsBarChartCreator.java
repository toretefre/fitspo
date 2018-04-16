package tdt4140.gr1806.app.ui;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import tdt4140.gr1806.app.core.DayWithStepsData;

import java.util.ArrayList;

public class StepsBarChartCreator {

    public static BarChart<String, Integer> getBarChart(ArrayList<DayWithStepsData> daysWithSteps) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        BarChart<String, Integer> barChart = new BarChart(xAxis, yAxis);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (DayWithStepsData dayWithSteps: daysWithSteps) {

            XYChart.Data<String, Integer> data = new XYChart.Data<>(
                    dayWithSteps.toString(), dayWithSteps.getSteps());

            series.getData().add(data);
        }

        series.setName("Skritt gått");

        barChart.getData().addAll(series);

        return barChart;
    }

}
