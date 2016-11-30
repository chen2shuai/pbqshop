package com.shop.web.entity;

import java.util.List;

public class DataGrid {
	private int total;
	private Object rows;
	
	public DataGrid(List dataList){
		total = dataList.size();
		rows = dataList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

}
