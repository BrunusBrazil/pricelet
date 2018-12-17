package com.wealth.common.chart;

import java.util.List;
import java.util.Map;

	public class ChartDTO {
	private List<Object> labels; 	
	private String type;
	private Map<String, List<Object>> data;
	public List<Object> getLabels() {
		return labels;
	}
	public void setLabels(List<Object> labels) {
		this.labels = labels;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, List<Object>> getData() {
		return data;
	}
	public void setData(Map<String, List<Object>> data) {
		this.data = data;
	}	
}
