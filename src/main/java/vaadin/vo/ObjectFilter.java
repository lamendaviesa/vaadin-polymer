package vaadin.vo;

public class ObjectFilter {
	
	private final String name;
	private final Object value; 
	
	public ObjectFilter(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

}
