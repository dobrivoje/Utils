package org.vaadin.highcharts;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.superbapps.utils.common.colors.AppealingColorGenerator;
import org.superbapps.utils.common.colors.IColorGenerator;
import org.vaadin.highcharts.AbstractHighChart;

public class HighChartGen extends AbstractHighChart {

    public HighChartGen() {
    }

    /**
     *
     * @param chartType Chart type
     * @param title Title for the chart to be displayed
     * @param series Set for the series of data to be shown
     * @param xAxisValues
     * @return Vaadin component to be typically embedded into layout component.
     * @throws java.lang.Exception
     */
    public Component generateHighChart(ChartType chartType, String title, List<Object> xAxisValues, Map<Object, List> series)
            throws Exception {
        return generateHighChart(chartType, title, xAxisValues, series, new AppealingColorGenerator(Color.GRAY, 0.7f));
    }

    /**
     *
     * @param chartType Chart type
     * @param title Title for the chart to be displayed
     * @param series Set for the series of data to be shown
     * @param xAxisValues
     * @param colorGenerator
     * @return Vaadin component to be typically embedded into layout component.
     * @throws java.lang.Exception
     */
    public Component generateHighChart(ChartType chartType, String title, List<Object> xAxisValues, Map<Object, List> series, IColorGenerator colorGenerator)
            throws Exception {
        HighChart hc = new HighChart();

        List<IColorGenerator> colorFactory = new ArrayList<>();

        for (int i = 0; i < series.size(); i++) {
            colorFactory.add(colorGenerator);
        }

        String out = generateHeader(title)
                + chartType.toString()
                + generateChartCategories(xAxisValues)
                + generateSeriesBegin()
                + generateSeries(series, colorFactory)
                + generateSeriesEnd();

        // ovo obezbeđuje da pri povećanju prozora koji drži ovu komponentu u full screen-u
        // ova komponenta se širi u obe ose do 93% !
        // isto tako, da je hc podešena sa setSizeFull, u legendi, kategorije bi bile na dnu
        // i ne bi se lepo videle. zato ostavljamo 7% marginu...
        hc.setHeight(93, Unit.PERCENTAGE);
        hc.setWidth(93, Unit.PERCENTAGE);
        hc.setResponsive(true);
        hc.setHcjs(out);

        return hc;
    }

    public Component generateHighChart(ChartType chartType, String title, List<Object> xAxisValues, Map<Object, List> series, List<IColorGenerator> colorFactory)
            throws Exception {
        HighChart hc = new HighChart();

        String out = generateHeader(title)
                + chartType.toString()
                + generateChartCategories(xAxisValues)
                + generateSeriesBegin()
                + generateSeries(series, colorFactory)
                + generateSeriesEnd();

        hc.setSizeFull();
        hc.setResponsive(true);
        hc.setHcjs(out);

        return hc;
    }

    //<editor-fold defaultstate="collapsed" desc="highchart generating methods">
    private String generateSeries(Map<Object, List> series, List<IColorGenerator> colorFactory) {
        String s = new String();

        int ind = 0;
        for (Map.Entry<Object, List> es : series.entrySet()) {
            Object key = es.getKey();
            List<Object> value = es.getValue();

            List c = colorFactory.get(ind).generateRGBColor(ind++);

            s += " { color: 'rgba(" + c.get(0) + "," + c.get(1) + "," + c.get(2) + "," + c.get(3) + ")', ";
            s += " name: '" + key + "', data: " + value + "} , ";
        }
        return s;
    }

    private String generateHeader(String title) {
        return "var options = { "
                + "title: {text: '" + title + "' }, ";
    }

    private String generateSeriesBegin() {
        return "series: [ ";
    }

    private String generateChartCategories(List<Object> xAxisValues) throws Exception {
        if (xAxisValues == null) {
            return "";
        } else {
            String s = new String();

            s = xAxisValues.stream().map((k) -> " ' " + k + " ', ").reduce(s, String::concat);

            s = s.substring(0, s.length() - 2);

            return "xAxis: {"
                    + "            categories: [ " + s + " ]"
                    + " },";
        }
    }

    private String generateSeriesEnd() {
        return " ]};";
    }
    //</editor-fold>

}
