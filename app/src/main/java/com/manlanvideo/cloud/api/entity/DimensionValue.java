package com.manlanvideo.cloud.api.entity;

public class DimensionValue extends BaseEntity {

	private static final long serialVersionUID = 8169100763367607326L;


	private Dimension dimension;


	private String value;

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
