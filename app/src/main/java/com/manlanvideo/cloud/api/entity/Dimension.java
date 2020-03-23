package com.manlanvideo.cloud.api.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Dimension extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 90331255808085016L;

	private String name;

	/**
	 *  状态：0-无效；1-有效
	 */

	private int status = 1;


	private Set<DimensionValue> dimensionValues = new HashSet<DimensionValue>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<DimensionValue> getDimensionValues() {
		return dimensionValues;
	}

	public void setDimensionValues(Set<DimensionValue> dimensionValues) {
		this.dimensionValues = dimensionValues;
	}
}
