package com.mark.service.jfreechat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by fellowlei on 2018/5/8.
 */
public class PieChartDemo {
    private static JFreeChart piePoltFont;
    private static JFreeChart legendTitle;
    private static JFreeChart piePoltNum;

    private static PieDataset getPieDataset(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("优秀(90-100)",23);
        dataset.setValue("良好(80-90)",45);
        dataset.setValue("中等(70-80)",34);
        dataset.setValue("及格(60-70)",20);
        dataset.setValue("不及格(< 60)",12);
        return dataset;
    }

    public static JFreeChart getJFreeChart() {
        PieDataset dataset = getPieDataset();
//        JFreeChart chart = ChartFactory.createPieChart("class 1", dataset, true, true, false);
        JFreeChart chart = ChartFactory.createPieChart3D("class 1", dataset, true, true, false);
        setPiePoltFont(chart);
        setLegendTitle(chart);
        setPiePoltNum(chart);
        createPiePlot(chart);

        chart.setAntiAlias(false);


        return chart;
    }

    private static void createPiePlot(JFreeChart chart) {
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setExplodePercent("优秀(90-100)",0.1);
        piePlot.setSectionOutlineStroke("优秀(90-100",new BasicStroke(3f));
        piePlot.setForegroundAlpha(0.9f);
    }


    public static void setPiePoltFont(JFreeChart chart) {
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setLabelFont(new Font("宋体",Font.PLAIN,14));

        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("宋体",Font.BOLD,22));

        LegendTitle legendTitle = chart.getLegend();
        legendTitle.setItemFont(new Font("宋体",Font.PLAIN,16));
    }

    public static void setLegendTitle(JFreeChart chart) {
        PieChartDemo.legendTitle = legendTitle;

    }

    public static void setPiePoltNum(JFreeChart chart) {
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}人,占{2}"));
    }


    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream("d:/tmp/pie_3d.jpeg");
        ChartUtilities.writeChartAsJPEG(out, 1.0f, getJFreeChart(),
                800, 450, null);// 输出图表
    }
}
