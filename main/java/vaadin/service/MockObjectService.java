package vaadin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.ui.TextField;

import vaadin.predicates.TPredicates;
import vaadin.utils.MessageUtil;
import vaadin.vo.GridElement;
import vaadin.vo.sort.ObjectSort;

/**
 * The class creates a MockService<T> where T defined the type of row.
 */
abstract class MockObjectService<T extends GridElement> implements ObjectService<T> {

	private List<T> rows;
	private T type = null;
	
	/**
	 * Constructor
	 * @param tDype
	 */
	public MockObjectService() {
		this.rows = new ArrayList<T>();
		init();
	}
	
	/**
	 * Getters & Setters
	 * @return
	 */
	public T getType() {
		return type;
	}

	public void setType(T type) {
		//TODO 
		// Assert.equals(null, this.id);
		if (type == null) throw new RuntimeException("Validation expected: " + type + " was: " + null);
		this.type = type;
	}
	
	public List<T> getRows() {
		return rows;
	}
	
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	/**
	 * Methods implemented to ObjectService 
	 */
	@Override
	public List<T> findAll() {
		return rows;
	}
	
	@Override
	public int count() {
		return findAll().size();
	}
	
	@Override
	public List<T> findAll(int offset, int limit) {
		if (rows.size() > limit) return rows.subList(offset, limit);
		return rows; 
	}

	@Override
	public List<T> findAllBySort(int offset, int limit, List<ObjectSort> sortOrders) {
		sortOrders.forEach(sort -> {
			String order = sort.isDescending() ? "DESC" : "ASC";
			MessageUtil.sqlExpression(sort.getPropertyName() + " = " + order);
		});
		if (rows.size() > limit) return rows.subList(offset, limit);
		return rows; 
	}
	
	@Override
	public List<T> findAllByFilter(int offset, int limit, List<TextField> collectionFilter) {
		Stream<T> stream = findAll(offset, limit).stream();
		
		List<Predicate<T>> predicates = TPredicates.predicates(collectionFilter);
		
		for (Predicate<T> predicate: predicates) {
			stream = stream.filter(predicate);
		}
		
		return stream.collect(Collectors.toList());
	}
	
	/**
	 * The implementation of the methods is delegated to the class that inherits
	 */
	public abstract void init();
	
}
