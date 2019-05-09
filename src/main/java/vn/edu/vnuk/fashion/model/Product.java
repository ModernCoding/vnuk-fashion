package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Product {
	private int id;
	
	@NotNull
	private String name;
	
	@NotNull
	private int subcategoryId;
	
	private int sleeveId;
	private int shapeId;
	private int collarId;
	private int heightId;
	private int materialId;
	
	@NotNull
	private int makerId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public int getSleeveId() {
		return sleeveId;
	}

	public void setSleeveId(int sleeveId) {
		this.sleeveId = sleeveId;
	}

	public int getShapeId() {
		return shapeId;
	}

	public void setShapeId(int shapeId) {
		this.shapeId = shapeId;
	}

	public int getCollarId() {
		return collarId;
	}

	public void setCollarId(int collarId) {
		this.collarId = collarId;
	}

	public int getHeightId() {
		return heightId;
	}

	public void setHeightId(int heightId) {
		this.heightId = heightId;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public int getMakerId() {
		return makerId;
	}

	public void setMakerId(int makerId) {
		this.makerId = makerId;
	}
	
	
}
