package portfolio;

import Core.Models.Year;
import Core.Root;

import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioForm {

    private Root root;
    private List<Year> years;
    private Year selectedYear;

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

    public Year getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(Year selectedYear) {
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
}
