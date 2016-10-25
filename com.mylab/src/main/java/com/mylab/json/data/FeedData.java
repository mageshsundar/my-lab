package com.mylab.json.data;

import java.util.Date;

public class FeedData {

	String id;
	String dataSetId;
	String dataSourceId;
	long dataAcquisitionTime;
	long dataNominalStartTime;
	long dataNominalEndTime;
	String createTime;
	String updateTime;
	String state;
	DataInstanceElements[] datasetinstanceelements;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDataSetId() {
		return dataSetId;
	}
	public void setDataSetId(String dataSetId) {
		this.dataSetId = dataSetId;
	}
	public String getDataSourceId() {
		return dataSourceId;
	}
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	public long getDataAcquisitionTime() {
		return dataAcquisitionTime;
	}
	public void setDataAcquisitionTime(long dataAcquisitionTime) {
		this.dataAcquisitionTime = dataAcquisitionTime;
	}
	public long getDataNominalStartTime() {
		return dataNominalStartTime;
	}
	public void setDataNominalStartTime(long dataNominalStartTime) {
		this.dataNominalStartTime = dataNominalStartTime;
	}
	public long getDataNominalEndTime() {
		return dataNominalEndTime;
	}
	public void setDataNominalEndTime(long dataNominalEndTime) {
		this.dataNominalEndTime = dataNominalEndTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public DataInstanceElements[] getDatasetinstanceelements() {
		return datasetinstanceelements;
	}
	public void setDatasetinstanceelements(DataInstanceElements[] datasetinstanceelements) {
		this.datasetinstanceelements = datasetinstanceelements;
	}
}
