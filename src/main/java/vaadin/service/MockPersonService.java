package vaadin.service;

import java.util.List;

import vaadin.vo.Person;

public class MockPersonService extends MockObjectService<Person> {

	private List<Person> persons;

	@Override
	public void init() {
		persons = super.getRows();
		for (int i = 0; i < 250; i++) {
			persons.add(new Person("Raquel", 30));
			persons.add(new Person("Raquel", 18));
			persons.add(new Person("Felicitas", 92));
			persons.add(new Person("Uriol", 2));
			persons.add(new Person("Albert", 15));
		}
		setRows(persons);
	}

}
