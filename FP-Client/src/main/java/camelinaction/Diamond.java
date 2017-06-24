package camelinaction;

import java.awt.geom.Point2D;

public class Diamond {
	private String id;
	private String color;
	private String clarity;
	private String cut;
	private String carat;
	private String price;
	
	public Diamond(String id, String color, String clarity, String cut, String carat, String price) {
		this.color = color;
		this.clarity = clarity;
		this.cut = cut;
		this.carat = carat;
		this.price = price;
		this.id = id;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getClarity() {
		return clarity;
	}
	
	public String getCut() {
		return cut;
	}
	
	public String getCarat() {
		return carat;
	}
	
	public String getPrice() {
		return price;
	}
	
	public Point2D.Double getPoint() {
		return new Point2D.Double(Double.parseDouble(carat), Double.parseDouble(price));
	}
	
	@Override
	public String toString() {
		return String.format("$%-15s | %-15s | %-15s | %-15s | %-15s | %-15s", price, carat, cut, color, clarity, id);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!Diamond.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final Diamond other = (Diamond) obj;
	    
	    return (this.id.equals(other.id));
	}


}
