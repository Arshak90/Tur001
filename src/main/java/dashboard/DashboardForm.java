package dashboard;

import Core.Models.Country;
import Core.Root;
import Core.Util;
import portfolio.Portfolio;
import portfolio.Portfoliocountry;
import portfolio.Portfoliomonthly;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class DashboardForm {

    public DashboardForm() {
    }

    private Root root;
    private List<Portfoliocountry> topTenPortfolioCountries;
    private LocalDate currentDate = LocalDate.now();
    private List<Portfolio> portfolios;
    private Map<Integer, Double> currentYearPortfoliomonthlies;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
        getCurrentYearPortfoliomonthlies();
    }

    public List<Portfoliocountry> getTopTenPortfolioCountries() {
        if(topTenPortfolioCountries == null){
            topTenPortfolioCountries = getRoot().getPortfolioDao().getPortfolioCountry().stream().filter(x-> filterPortfolioById(x.getPortfolioid())).collect(Collectors.toList());
        }
        return topTenPortfolioCountries;
    }

    public void setTopTenPortfolioCountries(List<Portfoliocountry> topTenPortfolioCountries) {
        this.topTenPortfolioCountries = topTenPortfolioCountries;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public List<Portfolio> getPortfolios() {
        return getRoot().getPortfolioDao().getAll().stream().filter(x-> x.getYear().equals(currentDate.getYear())).collect(Collectors.toList());
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public boolean filterPortfolioById(Integer id){
        return getPortfolios().stream().filter(x-> x.getId().equals(id)).findAny().isPresent();
    }

    public Country getCountryById(Integer id){
        return getRoot().getCountryDao().getAll().stream().filter(x-> id.equals(x.getId())).findFirst().get();
    }

    private Map<Integer, Integer> topTenPortfolioCountriesCalculated;
    public Map<Integer , Integer> getTopTenPortfolioCountriesCalculated(){
        return  getTopTenPortfolioCountries().
                stream().
                limit(10).
                collect(Collectors.groupingBy(Portfoliocountry::getCountryid, Collectors.summingInt(Portfoliocountry::getCount))).
                entrySet().
                stream().
                sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
    }

    public void setTopTenPortfolioCountriesCalculated(Map<Integer, Integer> topTenPortfolioCountriesCalculated) {
        this.topTenPortfolioCountriesCalculated = topTenPortfolioCountriesCalculated;
    }

    public Map<Integer, Double> getCurrentYearPortfoliomonthlies() {
        Map<Integer, Double> doubleList = Util.initMap();
        getRoot().getPortfolioDao().getPortfolioMonthly().stream().filter(x-> filterPortfolioById(x.getPortfolioid())).collect(Collectors.toList()).forEach(portfoliomonthly -> doubleList.put(portfoliomonthly.getMonthId(),portfoliomonthly.getFinances().doubleValue()));
        return doubleList;
    }

    public void setCurrentYearPortfoliomonthlies(Map<Integer, Double> currentYearPortfoliomonthlies) {
        this.currentYearPortfoliomonthlies = currentYearPortfoliomonthlies;
    }

    public String getCurrentYearPortfoliomonthliesString(){
        String s = "[";
        for(Integer doubleMap : getCurrentYearPortfoliomonthlies().keySet()){
            s = s + "[" + doubleMap + ", " + getCurrentYearPortfoliomonthlies().get(doubleMap) + "],";
        }
        return s + "],";
    }


    public String getFinaniceChartData(){
        return "\n" +
                "        [\n" +
                "          {\n" +
                "            data: "+getCurrentYearPortfoliomonthliesString()+"\n" +
                "            points: { show: true, radius: 5},\n" +
                "            splines: { show: true, tension: 0.45, lineWidth: 4, fill: 0.1}\n" +
                "          },\n" +
                "          { data: "+getCurrentYearPortfoliomonthliesString()+"\n" +
                "            bars: { show: true, barWidth: 0.05, align: 'center', lineWidth: 0, fillColor: { colors: [{ opacity: 0.1 }, { opacity: 0.1}] } }\n" +
                "          }\n" +
                "        ],\n" +
                "        {\n" +
                "          colors: ['#0cc2aa','#0cc2aa'],\n" +
                "          series: { shadowSize: 3 },\n" +
                "          xaxis: { show: true, font: { color: '#0cc2aa' }, position: 'bottom' },\n" +
                "          yaxis:{ show: false, font: { color: '#ccc' }},\n" +
                "          grid: { hoverable: true, clickable: true, borderWidth: 0, color: 'transparent' },\n" +
                "          tooltip: true,\n" +
                "          tooltipOpts: { content: '%x.0 is %y.2',  defaultTheme: false, shifts: { x: 0, y: -40 } }\n" +
                "        }\n" +
                "      ";
    }
}
