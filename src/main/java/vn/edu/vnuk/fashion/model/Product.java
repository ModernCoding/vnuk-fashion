package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {
	private Long id;
	
	@NotNull
	private Long subcategoryId;
	
	@NotNull
	private Long sleeveId;
	
	@NotNull
	private Long shapeId;
	
	@NotNull
	private Long collarId;
	
	@NotNull
	private Long heightId;
	
	@NotNull
	private Long materialId;
	
	@NotNull
	private Long makerId;
	
	@NotNull
	@Size(min = 1, message="Label is mandatory")
	private String name;
	
	private Subcategory subcategory;
	private Sleeve sleeve;
	private Shape shape;
	private Collar collar;
	private Height height;
	private Material material;
	private Maker maker;

	
	public Long getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public Long getSleeveId() {
		return sleeveId;
	}

	public void setSleeveId(Long sleeveId) {
		this.sleeveId = sleeveId;
	}

	public Long getShapeId() {
		return shapeId;
	}

	public void setShapeId(Long shapeId) {
		this.shapeId = shapeId;
	}

	public Long getCollarId() {
		return collarId;
	}

	public void setCollarId(Long collarId) {
		this.collarId = collarId;
	}

	public Long getHeightId() {
		return heightId;
	}

	public void setHeightId(Long heightId) {
		this.heightId = heightId;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public Long getMakerId() {
		return makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Sleeve getSleeve() {
		return sleeve;
	}

	public void setSleeve(Sleeve sleeve) {
		this.sleeve = sleeve;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Collar getCollar() {
		return collar;
	}

	public void setCollar(Collar collar) {
		this.collar = collar;
	}

	public Height getHeight() {
		return height;
	}

	public void setHeight(Height height) {
		this.height = height;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Maker getMaker() {
		return maker;
	}

	public void setMaker(Maker maker) {
		this.maker = maker;
	}

	
}
