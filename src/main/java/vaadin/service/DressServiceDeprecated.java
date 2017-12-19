package vaadin.service;

import java.util.List;

import vaadin.vo.Draws;
import vaadin.vo.ObjectSort;

public interface DressServiceDeprecated {
	List<Draws> findAll();
	List<Draws> findAll(int offset, int limit);
	List<Draws> findAll(int offset, int limit, List<ObjectSort> sortOrders);
	List<Draws> findAll(int offset, int limit, String filter);
	int count();
	int count(int offset, int limit);
	int count(int offset, int limit, List<ObjectSort> sortOrders);
	int count(int offset, int limit, String filter);
}