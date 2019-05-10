package vn.edu.vnuk.fashion.model;

import javax.validation.constraints.NotNull;

public class Product {
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Subcategory subcategory;
	
	private Sleeve sleeve;
	private Shape shape;
	private Collar collar;
	private Height height;
	private Material material;
	
	@NotNull
	private Maker maker;

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
