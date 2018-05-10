package com.mark.service.jfreechat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by fellowlei on 2018/5/2.
 */
public class TimeSerChartDemo {

    public static void main(String[] args) throws IOException {
        TimeSeries series2010 = new TimeSeries("2010年度");
        TimeSeries series2011 = new TimeSeries("2011年度");
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        int[] saleArr2010 = new int[12];
        int[] saleArr2011 = new int[12];
        int[] monthArr = new int[12];
        for(int i=0;i<12; i++){
            monthArr[i] = i+1;
            saleArr2010[i] = new Random().nextInt(1000);
            saleArr2011[i] = new Random().nextInt(1000);
        }
        for(int i=0; i<12; i++){
            series2010.add(new Month(monthArr[i],2011),saleArr2010[i]);
            series2011.add(new Month(monthArr[i],2011),saleArr2011[i]);
        }

        dataset.addSeries(series2010);
        dataset.addSeries(series2011);




        JFreeChart chart = ChartFactory.createTimeSeriesChart("A产品销售量", "", "", dataset, true, true, false);
        DateAxis dateaxis = (DateAxis)chart.getXYPlot().getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MM月"));
        chart.setBackgroundPaint(Color.WHITE);

        Font titleFont = new Font("黑体", Font.BOLD, 20);
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(titleFont);// 为标题设置上字体

        FileOutputStream out = new FileOutputStream("d:/tmp/time_ser_2.jpeg");
        ChartUtilities.writeChartAsJPEG(out, 1.0f, chart,
                800, 450, null);// 输出图表
    }
}
