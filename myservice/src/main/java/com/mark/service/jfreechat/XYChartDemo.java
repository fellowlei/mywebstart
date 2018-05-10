package com.mark.service.jfreechat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
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
public class XYChartDemo {
    private static XYDataset getDataset(){
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries timeSeriesA = new TimeSeries("A股");
        TimeSeries timeSeriesB = new TimeSeries("B股");
        TimeSeries timeSeriesC = new TimeSeries("C股");
        TimeSeries timeSeriesD = new TimeSeries("D股");
        for(int i=1;i<12; i++){
            timeSeriesA.add(new Month(i,2016),new Random().nextDouble()*9);
            timeSeriesB.add(new Month(i,2016),new Random().nextDouble()*8);
            timeSeriesC.add(new Month(i,2016),new Random().nextDouble()*6);
            timeSeriesD.add(new Month(i,2016),new Random().nextDouble()*4);
        }
        dataset.addSeries(timeSeriesA);
        dataset.addSeries(timeSeriesB);
        dataset.addSeries(timeSeriesC);
        dataset.addSeries(timeSeriesD);
        return dataset;
    }

    public static JFreeChart getTimeSeriesChart(){
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("", Font.BOLD,24));
        standardChartTheme.setRegularFont(new Font("", Font.BOLD,14));
        standardChartTheme.setLargeFont(new Font("", Font.BOLD,19));

        ChartFactory.setChartTheme(standardChartTheme);

        JFreeChart chart = ChartFactory.createTimeSeriesChart("股票价格走势图","月份","价格",getDataset()
            ,true,true,false);

        XYPlot plot = chart.getXYPlot();
        DateFormat format = new SimpleDateFormat("MM月份");
        DateAxis domainAxis = new DateAxis("2016年统计月份");
        DateTickUnit dut = new DateTickUnit(DateTickUnitType.DAY,29,format);
        domainAxis.setTickUnit(dut);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        return chart;
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream("d:/tmp/time_ser.jpeg");
        ChartUtilities.writeChartAsJPEG(out, 1.0f, getTimeSeriesChart(),
                800, 450, null);// 输出图表
    }



}
