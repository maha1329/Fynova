package tn.fynova.spring.service;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.repository.ProjectRepository;

@Service
@Transactional
public class StatistiqueProject  {
	@Autowired
	ProjectRepository projectRepository;
	
	private DonutChartModel donutModel;
	
	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

	@PostConstruct
    public void init() {
        
        createDonutModel();
        
    }
	
	//Statistique Projet
	public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(projectRepository.countByCategorie("informatique"));
        values.add(projectRepository.countByCategorie("agriculture"));
        values.add(projectRepository.countByCategorie("entrepreneuriat"));
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("informatique");
        labels.add("agriculture");
        labels.add("entrepreneuriat");
        data.setLabels(labels);

        donutModel.setData(data);
    }
	
	

}
