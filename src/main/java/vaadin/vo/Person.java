package vaadin.vo;

public class Person extends GridElement {
	private final String name;
	private final Integer age;
	
	public Person() {
		this.name = null;
		this.age = null;
	}
	
	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public Object getValue(String fieldName) {
		switch(fieldName) {
		case "name":
			return this.name;
		case "age":
			return this.age;
		default:
			return new Object();
		}
	}
	
	@Override
	public String toString() {
		return "[ name=" + name + ", age=" + age + "]";
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getAge() {
		return age;
	}
}
