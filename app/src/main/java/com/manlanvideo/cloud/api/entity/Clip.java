package com.manlanvideo.cloud.api.entity;

import java.util.HashSet;
import java.util.Set;


public class Clip extends BaseEntity {

	private static final long serialVersionUID = 369194168952842107L;

	// clip title
	private String title;

	// 简介
	private String description;

	// clip path
	private String path;



	private Set<ClipDimensionValue> clipDimensionValues = new HashSet<ClipDimensionValue>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ClipDimensionValue> getClipDimensionValues() {
		return clipDimensionValues;
	}

	public void setClipDimensionValues(Set<ClipDimensionValue> clipDimensionValues) {
		this.clipDimensionValues = clipDimensionValues;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
