package com.mark.service.jfreechat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by fellowlei on 2018/5/8.
 */
public class XYChartMonitorDemo {
    private static XYDataset getDataset(){
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries timeSeriesA = new TimeSeries("server1");
        TimeSeries timeSeriesB = new TimeSeries("server2");
        for(int i=1;i<60; i++){
            timeSeriesA.add(new Second(i,new Minute()),new Random().nextDouble());
            timeSeriesB.add(new Second(i,new Minute()),new Random().nextDouble());
        }
        dataset.addSeries(timeSeriesA);
        dataset.addSeries(timeSeriesB);
        return dataset;
    }

    public static JFreeChart getTimeSeriesChart(){
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("宋体", Font.BOLD,24));
        standardChartTheme.setRegularFont(new Font("宋体", Font.BOLD,14));
        standardChartTheme.setLargeFont(new Font("宋体", Font.BOLD,19));

        ChartFactory.setChartTheme(standardChartTheme);

        JFreeChart chart = ChartFactory.createTimeSeriesChart("second monitor","ms","percent",getDataset()
            ,true,true,false);

        XYPlot plot = chart.getXYPlot();
        DateFormat format = new SimpleDateFormat("hh:ss");
        DateAxis domainAxis = new DateAxis("momitor");
        DateTickUnit dut = new DateTickUnit(DateTickUnitType.SECOND,10,format);
        domainAxis.setTickUnit(dut);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        return chart;
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream("d:/tmp/time_ser_3.jpeg");
        ChartUtilities.writeChartAsJPEG(out, 1.0f, getTimeSeriesChart(),
                800, 450, null);// 输出图表
    }



}
