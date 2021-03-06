package org.vaadin.highcharts;

import com.vaadin.annotations.JavaScript;
import org.vaadin.highcharts.AbstractHighChart;

@JavaScript({"jquery-1.11.3.js", "highcharts.js", "highcharts-connector.js",
    "exporting.js", "highcharts-more.js" , "dark-unica.js", "piegradinetcolor.js"})
public class HighChartDark extends AbstractHighChart {

    private static final long serialVersionUID = -7975150479180453L;
}
