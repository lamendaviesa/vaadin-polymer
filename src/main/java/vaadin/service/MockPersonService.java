package vaadin.service;

import java.util.List;
import java.util.stream.Collectors;

import vaadin.vo.ObjectSort;
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

	public List<Person> find(int offset, int limit, String name, Integer age) {
		return findAll(offset, limit)
				.stream()
				.filter(p -> name == null || p.getName().toLowerCase().contains(name.toLowerCase()))
				.filter(p -> age == null || p.getAge().equals(age))
				.collect(Collectors.toList());

	}

	public int count(String name, Integer age) {
		return (int) findAll()
				.stream()
				.filter(p -> name == null || p.getName().toLowerCase().contains(name.toLowerCase()))
				.filter(p -> age == null || p.getAge().equals(age))
				.count();

	}

}
