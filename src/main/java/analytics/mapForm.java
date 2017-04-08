package analytics;

import Core.Models.Year;
import Core.Root;
import home.Country;
import portfolio.Portfolio;
import portfolio.Portfoliocountry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gev on 07.04.2017.
 */
public class MapForm {
    private List<Year> years;
    private Root root;
    private String firstYear;
    private String lastYear;
    private List<Portfolio> portfolios;
    private String json;
    private List<Portfoliocountry> portfoliocountries;
    private List<Country> countresWithCount = new ArrayList<>() ;
    private List<Core.Models.Country> countres ;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public List<Year> getYears() {
        if(this.years == null){
            this.years = getRoot().getYearDao().getAll();
        }
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }

    public String getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(String firstYear) {
        this.firstYear = firstYear;
    }

    public String getLastYear() {
        return lastYear;
    }

    public void setLastYear(String lastYear) {
        this.lastYear = lastYear;
    }

    public String getJson() {
        if (json == null){
            this.firstYear = "2017";
            this.lastYear = "2017";
            getFilter();
        }
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public List<Portfoliocountry> getPortfoliocountries() {
        if (this.portfoliocountries==null){
            this.portfoliocountries = getRoot().getPortfolioDao().getPortfolioCountry();
        }
        return portfoliocountries;
    }

    public void setPortfoliocountries(List<Portfoliocountry> portfoliocountries) {
        this.portfoliocountries = portfoliocountries;
    }

    public List<Core.Models.Country> getCountres() {
        if (this.countres == null) {
            this.countres = getRoot().getCountryDao().getAll();
        }
        return countres;
    }

    public void setCountres(List<Core.Models.Country> countres) {
        this.countres = countres;
    }

    public List<Portfolio> getPortfolios() {
        if (this.portfolios == null) {
            this.portfolios = getRoot().getPortfolioDao().getAll().stream().
                    filter(x -> (Integer.valueOf(this.firstYear) <= x.getYear() && x.getYear() <=Integer.valueOf(this.lastYear))).
                    collect(Collectors.toList());
        }
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public List<Country> getCountresWithCount() {
        if(this.countresWithCount==null || this.countresWithCount.size()==0){
            initCountryWithCountList();
        }
        return countresWithCount;
    }

    public void setCountresWithCount(List<Country> countresWithCount) {
        this.countresWithCount = countresWithCount;
    }

    public void initCountryWithCountList(){
        ArrayList<Portfoliocountry> newgetPortfoliocountries = new ArrayList<>();
        for (Portfolio portfolio :this.getPortfolios()){
            newgetPortfoliocountries.addAll(this.getPortfoliocountries().stream().filter(x->portfolio.getId().equals(x.getPortfolioid())).collect(Collectors.toList()));
        }
        this.countresWithCount= new ArrayList<>();
        for (Core.Models.Country country : this.getCountres()){

            Integer count = 0;
            for (Portfoliocountry pc : newgetPortfoliocountries){
                if (pc.getCountryid().equals(country.getId())){
                    count+= pc.getCount();
                }
            }
            this.countresWithCount.add(new Country(country.getId(),country.getName(),country.getName_ENG(),count));
        }
    }

    public void getFilter() {
        if (!this.firstYear.equals("") || !this.lastYear.equals("")) {
            this.setPortfolios(null);
            initCountryWithCountList();
            this.json = "{\n" +
                    "tooltip : {\n" +
                    "    trigger: 'item',\n" +
                    "   formatter : function (params) {\n" +
                    "var value = (params.value + '').split('.'); value = value[0].replace(/(d{1,3})(?=(?:d{3})+(?!d))/g, '$1,')\n" +
                    "+ '.' + value[1];\n" +
                    "return  params.data.hname + ' - ' + params.value;\n" +
                    "}\n" +
                    "},\n" +
                    "dataRange: {\n" +
                    "min: 0,\n" +
                    "    max: 10000,\n" +
                    "   text:['Շատ','Քիչ'],\n" +
                    "realtime: false,\n" +
                    "   calculable : true,\n" +
                    "   color: ['#0d8878','#61BBA8','#B8DFD7']\n" +
                    "},\n" +
                    "series : [\n" +
                    "{\n" +
                    "name: 'Մարդկանց թիվը',\n" +
                    "    type: 'map',\n" +
                    "mapType: 'world',\n" +
                    "roam: true,\n" +
                    "mapLocation: {\n" +
                    "y : 10\n" +
                    "},\n" +
                    "itemStyle:{\n" +
                    "emphasis:{label:{show:true}}\n" +
                    "},\n" +
                    "data:[\n";
            String country = "";
            for (Country countresWithCount : this.getCountresWithCount()) {
                country += "{name : '" + countresWithCount.getName_ENG() + "',hname: '" + countresWithCount.getName() + "', value : " + countresWithCount.getCount() + "},\n";
            }
            country = country.substring(0, country.length() - 2) + "\n";
            this.json = json + country + "]\n" +
                    "                  }\n" +
                    "              ]\n" +
                    "            }";


        }
    }
}
