package vaadin.vo.sort;

public class ObjectSort {
	private String propertyName;
	private boolean descending;
	
	public ObjectSort(String propertyName , boolean descending) {
		this.propertyName = propertyName;
		this.descending = descending;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public boolean isDescending() {
		return descending;
	}
	public void setDescending(boolean descending) {
		this.descending = descending;
	}

	@Override
	public String toString() {
		return "[ " + propertyName + ", " + descending + " ]";
	}
	
	
}
