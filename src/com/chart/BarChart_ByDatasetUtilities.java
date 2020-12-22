package com.chart;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;


public class BarChart_ByDatasetUtilities {

    public static String generateBarChart(HttpSession session,double[][] data) throws IOException {
        /**
         * 以下代码通过设置主题样式解決中文乱码问题
         */
        // 创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        // 设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
        // 设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
        // 应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);

        String[] rowKeys = new String[] { "人数" };
        String[] columnKeys = new String[] { "<60", ">=60&&<70", ">=70&&<80", ">=80&&<90",">=90" };
        // 利用DatasetUtilities工具类创建CategoryDataset
        CategoryDataset dataSet = DatasetUtilities.createCategoryDataset(rowKeys, // 比较的内容数组（肉类）
                columnKeys, // 比较的分类数组（地区分类）
                data // 比较的数据
        );
        // 调用ChartFactory工厂类生成3D柱状图
        JFreeChart barChart = ChartFactory.createBarChart("分数分布统计图", // 图表的标题
                "分数段", // 比较的分类标签
                "人数", // 比较的内容标签
                dataSet, // 比较的数据集
//    		PlotOrientation.HORIZONTAL,		//图表显示方向：水平方向
                PlotOrientation.VERTICAL, // 图表显示方向：垂直方向（默认）
                true, // a flag specifying whether or not a legend is required
                true, // configure chart to generate tool tips
                true // configure chart to generate URLs
        );

        /**
         * 设置图表样式：通过matlab的plot（）函数设置图表样式，使得图表更加美观
         */
        CategoryPlot plot = barChart.getCategoryPlot();
        // 设置网格背景颜色
        plot.setBackgroundPaint(Color.WHITE);
        // 设置网格竖线颜色
        plot.setDomainGridlinePaint(Color.PINK);
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.pink);

        // 显示每个柱的数值，并修改数值的字体属性（renderer渲染器）
        BarRenderer3D renderer = new BarRenderer3D();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);

        renderer.setBasePositiveItemLabelPosition(
                new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
        renderer.setItemLabelAnchorOffset(10D);

        // 设置平行柱之间的间距
        renderer.setItemMargin(0.4);
        // 将样式应用到CategoryPlot
        plot.setRenderer(renderer);

        // 使用ServletUtilities将工厂类生成的图表保存为png图片
        String fileName = ServletUtilities.saveChartAsPNG(barChart, // 工厂类生成的图表
                700, // 要生成图片宽度
                500, // 要生成图片的高度
                session // HttpSession
        );

        // 返回图片的文件名
        return fileName;
    }
}
