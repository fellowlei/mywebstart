package com.mark.service.jfreechat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by fellowlei on 2018/5/8.
 */
public class BarChartDemo {

    private static CategoryDataset getCategoryDataset(){
        DefaultKeyedValues keyedValues = new DefaultKeyedValues();
        keyedValues.setValue("优秀",23);
        keyedValues.setValue("良好",45);
        keyedValues.setValue("中等",34);
        keyedValues.setValue("及格",20);
        keyedValues.setValue("不及格",12);

        CategoryDataset dataset = DatasetUtilities.createCategoryDataset("英语成绩",keyedValues);
        return  dataset;
    }

    public static JFreeChart getJFreeChart(){
        CategoryDataset dataset = getCategoryDataset();

//        JFreeChart chart = ChartFactory.createBarChart("英语成绩统计",null,"人数",dataset,
//                PlotOrientation.VERTICAL,false,false,false);
        JFreeChart chart = ChartFactory.createBarChart3D("英语成绩统计",null,"人数",dataset,
                PlotOrientation.VERTICAL,false,false,false);
        updateFont(chart);
        return chart;
    }

    private static void updateFont(JFreeChart chart) {
        //1. 图形标题文字设置
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("宋体",Font.BOLD,20));

        //2. 图形X轴坐标文字的设置
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setLabelFont(new Font("宋体",Font.BOLD,14));  //设置X轴坐标上标题的文字
        axis.setTickLabelFont(new Font("宋体",Font.BOLD,14));  //设置X轴坐标上的文字

        //2. 图形Y轴坐标文字的设置
        ValueAxis valueAxis = plot.getRangeAxis();
        valueAxis.setLabelFont(new Font("宋体",Font.BOLD,14));  //设置Y轴坐标上标题的文字
        valueAxis.setTickLabelFont(new Font("sans-serif",Font.BOLD,14));//设置Y轴坐标上的文字

    }

    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream("d:/tmp/bar_3d.jpeg");
        ChartUtilities.writeChartAsJPEG(out, 1.0f, getJFreeChart(),
                800, 450, null);// 输出图表
    }


}
