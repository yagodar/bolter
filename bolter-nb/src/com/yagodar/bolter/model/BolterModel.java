/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author АППДКт78М
 */
public class BolterModel {
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
     
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public ArrayList<String> getListSites() {
        return listSites;
    }

    public void setListSites(ArrayList<String> listSites) {
        this.listSites = listSites;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public ArrayList<String> getListEngines() {
        return listEngines;
    }

    public void setListEngines(ArrayList<String> listEngines) {
        this.listEngines = listEngines;
    }
        
    private String search;
    private String site;
    private ArrayList<String> listSites;
    private Date dateFrom;
    private Date dateTo;
    private ArrayList<String> listEngines;
}
