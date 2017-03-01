package portfolio;

import Core.Models.Month;
import Core.Models.Year;
import Core.Root;
import Core.Util;
import Models.Sights;
import Models.Transport;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import java.lang.ref.ReferenceQueue;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioForm {

    public PortfolioForm() {
    }

    private Root root;
    private List<Year> years;
    private String selectedYear;
    private Portfolio selectedPortfolio;
    private List<Portfolio> portfolios  = new ArrayList<>();
    private List<Transport> transports;
    private List<Sights> sights;
    private List<Portfoliosights> portfoliosightses;
    private Portfoliomonthly portfoliomonthly = new Portfoliomonthly();
    final static Logger logger = Logger.getLogger(String.valueOf(PortfolioForm.class));

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

    public List<Transport> getTransports() {
        if(transports == null){
            return getRoot().getPortfolioDao().getTransport();
        }
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public String getSelectedYear() {
        if(selectedYear == null || selectedYear == ""){
            selectedYear = String.valueOf(new Date().getYear() + 1900);
            changeYear();
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

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public void changeYear(){
        this.portfolios = getRoot().getPortfolioDao().getAll().stream().
                        filter(x -> selectedYear.equals(x.getYear().toString())).
                        collect(Collectors.toList());
    }

    public Portfolio getSelectedPortfolio() {
        return selectedPortfolio;
    }

    public void setSelectedPortfolio(Portfolio selectedPortfolio) {
        this.selectedPortfolio = selectedPortfolio;
    }

    public void editPortfolio(Portfolio portfolio) {
        this.selectedPortfolio = portfolio;
    }

    public void save(){
        if(selectedPortfolio.getArmtouristcount() + selectedPortfolio.getOthertouristcount() == selectedPortfolio.getTotaltouristcount()){
            getRoot().getPortfolioDao().update(selectedPortfolio);
            logger.info("Saved successfully " + selectedPortfolio.toString());
            selectedPortfolio = null;
            RequestContext.getCurrentInstance().update("panelGroupView");
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "What we do in life", "Echoes in eternity.");

            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public List<Portfoliosights> getPortfoliosightses() {
        return  portfoliosightses = getRoot().getPortfolioDao().getPortfolioSights();
    }

    public void setPortfoliosightses(List<Portfoliosights> portfoliosightses) {
        this.portfoliosightses = portfoliosightses;
    }



    public List<Sights> getSights() {
        return getRoot().getPortfolioDao().getSightses().stream().filter(x-> !filterQuarterSights(x.getId())).collect(Collectors.toList());
    }

    public List<Sights> getSightsAll() {
        return getRoot().getPortfolioDao().getSightses();
    }

    public void setSights(List<Sights> sights) {
        this.sights = sights;
    }

    private Integer sightId;

    public Integer getSightId() {
        return sightId;
    }

    public void setSightId(Integer sightId) {
        this.sightId = sightId;
    }

    public void addPortfolioSight(){
        if(selectedPortfolio != null && sightId != null){
            getRoot().getPortfolioDao().insertPortfoliosights(selectedPortfolio.getId(), sightId);
            sightId = null;
            portfoliosightses = null;
        }
    }

    public List<Portfoliosights> getPortfoliosightsesByPortfolioId(){
        return getPortfoliosightses().stream().filter(x -> selectedPortfolio.getId().equals(x.getPortfolioid())).collect(Collectors.toList());
    }

    public List<Portfoliomonthly> getPortfoliomonthlyByPortfolioId(){
        return getRoot().getPortfolioDao().getPortfolioMonthly().stream().filter(x -> selectedPortfolio.getId().equals(x.getPortfolioid())).collect(Collectors.toList());
    }

    public Portfoliomonthly getPortfoliomonthly() {
        return portfoliomonthly;
    }

    public void setPortfoliomonthly(Portfoliomonthly portfoliomonthly) {
        this.portfoliomonthly = portfoliomonthly;
    }

    public void addPortfoliomonthly(){
        if(selectedPortfolio != null && portfoliomonthly.getMonthId() != null){
            portfoliomonthly.setPortfolioid(selectedPortfolio.getId());
            getRoot().getPortfolioDao().insertPortfoliomonthly(portfoliomonthly);
            portfoliomonthly = new Portfoliomonthly();
        }
    }

    private Map<String, List<String>> quarterMonth;

    public List<Month> getQuarterMonth() {
        return Util.getMonths().stream().filter(x -> selectedPortfolio.getQuarter().equals(x.getQuarter()) && !filterQuarterMonth(x.getId())).collect(Collectors.toList());
    }

    public void setQuarterMonth(Map<String, List<String>> quarterMonth) {
        this.quarterMonth = quarterMonth;
    }

    public boolean filterQuarterMonth(Integer monthId){
        return getPortfoliomonthlyByPortfolioId().stream().filter(x-> x.getMonthId().equals(monthId)).findAny().isPresent();
    }

    public boolean filterQuarterSights(Integer sightsId){
        return getPortfoliosightsesByPortfolioId().stream().filter(x-> x.getSights().getId().equals(sightsId)).findAny().isPresent();
    }

    public String getMonthNameById(Integer id){
        return Util.getMonths().get(id-1).getName();
    }

    public void deletePortfoliosightses(Portfoliosights portfoliosights){
        getRoot().getPortfolioDao().deletePortfoliosightses(portfoliosights.getId());
    }

    public void deletePortfoliomonthly(Portfoliomonthly portfoliomonthly){
        getRoot().getPortfolioDao().deletePortfoliomonthly(portfoliomonthly.getId());
    }


}
