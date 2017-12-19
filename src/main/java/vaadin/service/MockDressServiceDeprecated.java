package vaadin.service;

import java.util.ArrayList;
import java.util.List;

import vaadin.vo.Draws;
import vaadin.vo.ObjectSort;

public class MockDressServiceDeprecated implements DressServiceDeprecated {

	private List<Draws> dresses = new ArrayList<>();
	private List<Draws> result = new ArrayList<>();

	public MockDressServiceDeprecated() {
		init();
	}
	
	@Override
	public List<Draws> findAll() {
		return dresses;
	}
	
	@Override
	public int count() {
		return dresses.size();
	}
	
	@Override
	public List<Draws> findAll(int offset, int limit) {
		if (dresses.size() > limit) return dresses.subList(offset, limit);
		return dresses; 
	}
	
	@Override
	public int count(int offset, int limit) {
		return findAll(offset, limit).size();
	}

	@Override
	public List<Draws> findAll(int offset, int limit, List<ObjectSort> sortOrders) {
		//TODO order
		sortOrders.forEach(sort -> {
			//MessageUtil.sqlExpression(sort.getPropertyName() + " = " + sort.isDescending() ? "DESC" : "ASC");
		});
		if (dresses.size() > limit) return dresses.subList(offset, limit);
		return dresses; 
	}

	@Override
	public int count(int offset, int limit,  List<ObjectSort> sortOrders) {
		return findAll(offset, limit, sortOrders).size();
	}
	
	@Override
	public List<Draws> findAll(int offset, int limit, String filter) {
		dresses.forEach(d -> {
			if (d.getValue4().toUpperCase().contains(filter.toUpperCase())) result.add(d);
		});
		if (result.size() > limit) result.subList(offset, limit);
		return result;
	}


	@Override
	public int count(int offset, int limit, String filter) {
		return  findAll(offset, limit, filter).size();
	}
	
	private void init() {
		for (int i = 0; i < 250; i++) {
			dresses.add(new Draws("mini_dress.png", "mini", 15.95, "Mini"));
			dresses.add(new Draws("plaid_dress.png", "plaid", 22.95, "Plaid"));
			dresses.add(new Draws("flower_dress.png", "flower", 22.95, "Flower"));
			dresses.add(new Draws("pink_dress.png", "pink", 22.95, "Pink"));
		}
	}


}

