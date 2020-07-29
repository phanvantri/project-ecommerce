package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.model.ChartProduct;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class DashBoardController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrderById/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getById(id);
    }

    @GetMapping("/countorderoneweek")
    public List<BarChart> countOrderByWeek() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = java.time.LocalDate.now();
        List<BarChart> chart = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            BarChart c = new BarChart();
            c.setLabel(localDate);
            Date dateStart = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            Date dateEnd = Date.from(localDate.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());
            c.setY(orderService.countOrderByDate(dateStart, dateEnd).size());
            chart.add(c);
            localDate = localDate.minusDays(1);
        }
        Collections.sort(chart, Comparator.comparing(BarChart::getLabel));
        return chart;
    }

    @GetMapping("/summoneyonemounth")
    public List<ChartByMounth> sumMoneyOneMounth() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate endDate = java.time.LocalDate.now();
        LocalDate startDate = endDate.with(ChronoField.DAY_OF_MONTH, 1);
        LocalDate endDate2 = startDate.plusDays(-1);
        LocalDate startDate2 = endDate2.with(ChronoField.DAY_OF_MONTH, 1);
        LocalDate endDate3 = startDate2.plusDays(-1);
        LocalDate startDate3 = endDate3.with(ChronoField.DAY_OF_MONTH, 1);
        List<ChartByMounth> lstChartByMounth = new ArrayList<>();
        ChartByMounth chartByMounth1 = new ChartByMounth();
        ChartByMounth chartByMounth2 = new ChartByMounth();
        ChartByMounth chartByMounth3 = new ChartByMounth();
       // chartByMounth1.setYValueFormatString("$#,###");
       // chartByMounth1.setXValueFormatString("DD");
        chartByMounth1.setType("line");
        chartByMounth1.setShowInLegend(true);
        //chartByMounth2.setYValueFormatString("$#,###");
       // chartByMounth2.setXValueFormatString("DD");
        chartByMounth2.setType("line");
        chartByMounth2.setShowInLegend(true);
        //chartByMounth3.setYValueFormatString("$#,###");
       // chartByMounth3.setXValueFormatString("DD");
        chartByMounth3.setType("line");
        chartByMounth3.setShowInLegend(true);
        List<Chart> chart = new ArrayList<>();
        endDate.plusDays(1);
        int i=1;
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            Chart c = new Chart();
            c.setX(i++);
            Date dateStart = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
            Date dateEnd = Date.from(date.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());
            int sum = 0;
            List<Order> lst = orderService.countOrderByDate(dateStart, dateEnd);
            for (Order s : lst) {
                sum += s.getTotalprice();
            }

            c.setY(sum / 1000);
            chart.add(c);
        }

        Collections.sort(chart, Comparator.comparing(Chart::getX));
        chartByMounth1.setDataPoints(chart);
        chartByMounth1.setName(startDate.toString());
        lstChartByMounth.add(chartByMounth1);
        /////
        List<Chart> chart2 = new ArrayList<>();
        LocalDate temp = endDate2.plusDays(1);
        i=1;
        for (LocalDate date = startDate2; date.isBefore(temp); date = date.plusDays(1)) {
            Chart c = new Chart();
            c.setX(i++);
            Date dateStart = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
            Date dateEnd = Date.from(date.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());
            int sum = 0;
            List<Order> lst = orderService.countOrderByDate(dateStart, dateEnd);
            for (Order s : lst) {
                sum += s.getTotalprice();
            }

            c.setY(sum / 1000);
            chart2.add(c);
        }
        Collections.sort(chart2, Comparator.comparing(Chart::getX));
        chartByMounth2.setDataPoints(chart2);
        chartByMounth2.setName(startDate2.toString());
        lstChartByMounth.add(chartByMounth2);
        //////
        List<Chart> chart3 = new ArrayList<>();
        LocalDate temp3 = endDate3.plusDays(1);
        i=1;
        for (LocalDate date = startDate3; date.isBefore(temp3); date = date.plusDays(1)) {
            Chart c = new Chart();
            c.setX(i++);
            Date dateStart = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
            Date dateEnd = Date.from(date.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());
            int sum = 0;
            List<Order> lst = orderService.countOrderByDate(dateStart, dateEnd);
            for (Order s : lst) {
                sum += s.getTotalprice();
            }

            c.setY(sum / 1000);
            chart3.add(c);
        }
        Collections.sort(chart3, Comparator.comparing(Chart::getX));
        chartByMounth3.setDataPoints(chart3);
        chartByMounth3.setName(startDate3.toString());
        lstChartByMounth.add(chartByMounth3);
        return lstChartByMounth;


    }


    @GetMapping("/summoneyoneweek")
    public List<ChartM> sumMoneyOneWeek() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = java.time.LocalDate.now();
        List<ChartM> chart = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            ChartM c = new ChartM();
            c.setX(localDate);
            Date dateStart = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            Date dateEnd = Date.from(localDate.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());
            int sum = 0;
            List<Order> lst = orderService.countOrderByDate(dateStart, dateEnd);
            for (Order s : lst) {
                sum += s.getTotalprice();
            }

            c.setY(sum / 1000);
            chart.add(c);
            localDate = localDate.minusDays(1);
        }
        Collections.sort(chart, Comparator.comparing(ChartM::getX));
        return chart;
    }

    @GetMapping("/countproducttop")
    public List<ChartProduct> getCountProductTop() {
        return orderService.countProductOrderTop();
    }

@Data
private class Chart {
    private int x;
    private int y;
}
    @Data
    private class ChartM {
        private LocalDate x;
        private int y;
    }

@Data
private class BarChart {
    private LocalDate label;
    private int y;
}

@Data
private class ChartByMounth {
    private String type;
    private String name;
    private boolean showInLegend;
    private List<Chart> dataPoints;
}
}
