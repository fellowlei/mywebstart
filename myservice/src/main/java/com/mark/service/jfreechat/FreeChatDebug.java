package com.mark.service.jfreechat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by fellowlei on 2018/5/2.
 */
public class FreeChatDebug {

    private static DefaultPieDataset getDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("苹果", 100);
        dataset.setValue("梨子", 200);
        dataset.setValue("葡萄", 300);
        dataset.setValue("香蕉", 400);
        dataset.setValue("荔枝", 500);
        return dataset;
    }

    public static void main(String[] args) throws IOException {
        DefaultPieDataset data = getDataSet();
        JFreeChart chart = ChartFactory.createPieChart3D("水果产量图", // 图表标题
                data, // 数据集
                true, // 是否显示图例
                false, // 是否生成工具
                false // 是否生成URL链接
        );// 创建图表




        Font titleFont = new Font("黑体", Font.BOLD, 20);
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(titleFont);// 为标题设置上字体

        Font plotFont = new Font("宋体", Font.PLAIN, 16);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(plotFont); // 为饼图元素设置上字体

        Font LegendFont = new Font("楷体", Font.PLAIN, 18);
        LegendTitle legend = chart.getLegend(0);
        legend.setItemFont(LegendFont);// 为图例说明设置字体


        FileOutputStream out = new FileOutputStream("d:/tmp/chat.jpeg");
        ChartUtilities.writeChartAsJPEG(out, 1.0f, chart,
                800, 450, null);// 输出图表
    }



}
