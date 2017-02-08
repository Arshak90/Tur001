package portfolio;

import Core.Models.Year;
import Core.Root;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioForm {

    private Root root;
    private List<Year> years;
    private String selectedYear;

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

    public String getSelectedYear() {
        if(selectedYear == null || selectedYear == ""){
            selectedYear = String.valueOf(new Date().getYear() + 1900);
        }
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    public void reset(){
        System.out.println("AAA");
    }

    private String console;

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    private List<Portfolio> portfolios  = new ArrayList<>();

    public List<Portfolio> getPortfolios() {
        if(portfolios.size() == 0){
            Portfolio p1 = new Portfolio();
            p1.setId(1);
            p1.setQuarter(1);
            Portfolio p2 = new Portfolio();
            p2.setId(2);
            p2.setQuarter(2);
            Portfolio p3 = new Portfolio();
            p3.setId(3);
            p3.setQuarter(3);
            Portfolio p4 = new Portfolio();
            p4.setId(4);
            p4.setQuarter(4);
            portfolios.add(p1);
            portfolios.add(p2);
            portfolios.add(p3);
            portfolios.add(p4);
        }
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }
}
