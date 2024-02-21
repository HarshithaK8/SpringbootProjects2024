package com.locationproject.location.Util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class ReportUtilImpl implements ReportUtil{
    @Override
    public void generatePieCharts(String path, List<Object[]> data) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        for(Object[] objects : data){
            dataset.setValue(objects[0].toString(),
                    new Double(objects[1].toString()));
        }

        JFreeChart chart = ChartFactory.createPieChart3D("Location type report",dataset,true,true,true);
        try {
            ChartUtilities.saveChartAsJPEG(new File(path+"/pieChart.jpeg"),
                    chart,300,300);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
