package com.manlanvideo.cloud.api.entity;

import java.io.Serializable;
import java.util.Date;


public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -9035314212268431767L;


	private long id;

	private Date createdAt;

	private Date updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
