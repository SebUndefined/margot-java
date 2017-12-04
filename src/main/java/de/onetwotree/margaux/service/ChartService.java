package de.onetwotree.margaux.service;


import de.onetwotree.margaux.entity.Harvest;
import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.Resource;
import de.onetwotree.margaux.entity.ResourceType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SebUndefined on 29/08/17.
 */
public interface ChartService {


    String buildLineChartByDate(Map<Resource, List<Harvest>> resourceWithHarvests);

    String buildLineChartHarvestWithYear(Map<Resource, Map<Integer, BigDecimal>> map);

    String buildLineChartHarvestWithYearByPlot(Map<String, Map<Integer, BigDecimal>> map);

    String buildPieChart(List<String> labels, List<String> values);
}
