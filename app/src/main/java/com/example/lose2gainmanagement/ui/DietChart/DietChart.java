package com.example.lose2gainmanagement.ui.DietChart;

import java.io.Serializable;
import java.util.List;

public class DietChart implements Serializable {

    private String cName;
    private String cWeight;
    private String cHeight;
    private String cAge;
    private String cSex;
    private String cMedicalProblem;
    private String instruction;
    private String chartName;
    private String chartDate;
    private List<DietChartBela> belas;

    public DietChart(String cName, String cWeight, String cHeight, String cAge, String cSex, String cMedicalProblem,String chartName, String chartDate, String instruction, List<DietChartBela> belas) {
        this.cName = cName;
        this.cWeight = cWeight;
        this.cHeight = cHeight;
        this.cAge = cAge;
        this.cSex = cSex;
        this.cMedicalProblem = cMedicalProblem;
        this.instruction = instruction;
        this.chartName = chartName;
        this.chartDate = chartDate;
        this.belas = belas;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public String getChartDate() {
        return chartDate;
    }

    public void setChartDate(String chartDate) {
        this.chartDate = chartDate;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcWeight() {
        return cWeight;
    }

    public void setcWeight(String cWeight) {
        this.cWeight = cWeight;
    }

    public String getcHeight() {
        return cHeight;
    }

    public void setcHeight(String cHeight) {
        this.cHeight = cHeight;
    }

    public String getcAge() {
        return cAge;
    }

    public void setcAge(String cAge) {
        this.cAge = cAge;
    }

    public String getcSex() {
        return cSex;
    }

    public void setcSex(String cSex) {
        this.cSex = cSex;
    }

    public String getcMedicalProblem() {
        return cMedicalProblem;
    }

    public void setcMedicalProblem(String cMedicalProblem) {
        this.cMedicalProblem = cMedicalProblem;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<DietChartBela> getBelas() {
        return belas;
    }

    public void setBelas(List<DietChartBela> belas) {
        this.belas = belas;
    }
}
