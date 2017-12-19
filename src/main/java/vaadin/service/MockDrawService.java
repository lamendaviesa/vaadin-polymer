package vaadin.service;

import java.util.List;

import vaadin.vo.Draws;

public class MockDrawService extends MockObjectService<Draws> {

	private List<Draws> draws;
	
	@Override
	public void init() {
		draws = super.getRows();
		for (int i = 0; i < 250; i++) {
			draws.add(new Draws("pentagon.png", "mini", 15.95, "Red"));
			draws.add(new Draws("rectangle.png", "plaid", 22.95, "Green"));
			draws.add(new Draws("rhombus.png", "flower", 22.95, "Orange"));
		}
	}

}

