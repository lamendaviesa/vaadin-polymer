package vaadin.service;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.TextField;

import vaadin.vo.GridElement;
import vaadin.vo.ObjectSort;

/**
 * The class creates a MockService<T> where T defined the type of row.
 */
abstract class MockObjectService<T extends GridElement> implements ObjectService<T> {

	private List<T> rows;
	private List<T> result;
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
		if (rows.size() > limit) return rows.subList(offset, offset + limit - 1);
		return rows; 
	}

	@Override
	public int count(int offset, int limit) {
		return findAll(offset, limit).size();
	}
	
	@Override
	public List<T> findAll(int offset, int limit, List<ObjectSort> sortOrders) {
		sortOrders.forEach(sort -> {
			//MessageUtil.sqlExpression(sort.getPropertyName() + " = " + sort.isDescending() ? "DESC" : "ASC");
		});
		if (rows.size() > limit) return rows.subList(offset, limit);
		return rows; 
	}

	@Override
	public int count(int offset, int limit,  List<ObjectSort> sortOrders) {
		return findAll(offset, limit, sortOrders).size();
	}
	
	@Override
	public int count(int offset, int limit, TextField filter) {
		return findAll(offset, limit, filter).size();
	}
	
	@Override
	public int count(List<TextField> collectionFilters) {
		return findAll(collectionFilters).size();
	}
	
	public List<T> findAll(int offset, int limit, TextField filter) {
		result = new ArrayList<T>();
		
		if (filter.getValue().trim().isEmpty()) {
			return getRows();
		}
		
		for (T row : getRows()) {
			Object valueRow = row.getValue(filter.getCaption());
			if (valueRow instanceof String) {
				String value = (String) valueRow;
				if (value.toUpperCase().contains(filter.getValue().toUpperCase())) result.add(row);
			}
			if (valueRow instanceof Integer) {
				Integer value = (Integer) valueRow;
				if (value == Integer.valueOf(filter.getValue())) result.add(row);
			}
			if (valueRow instanceof Double) {
				Double value = (Double) valueRow;
				if (value == Double.valueOf(filter.getValue())) result.add(row);
			}
		}
		
//		getRows().forEach(row -> {
//			Object valueRow = row.getValue(valueFilter);
//			if (valueRow instanceof String) {
//				String value = (String) valueRow;
//				if (value.toUpperCase().contains(valueFilter.toUpperCase())) result.add(row);
//			}
//			if (valueRow instanceof Integer) {
//				Integer value = (Integer) valueRow;
//				if (value == Integer.valueOf(valueFilter)) result.add(row);
//			}
//			if (valueRow instanceof Double) {
//				Double value = (Double) valueRow;
//				if (value == Double.valueOf(valueFilter)) result.add(row);
//			}
//		});
		if (result.size() > limit) result.subList(offset, limit);
		return result;
	}
	
	public List<T> findAll(List<TextField> collectionFilters) {
		result = new ArrayList<T>();

		collectionFilters.forEach( textField -> {
			String valueFilter = textField.getValue();
			if (!valueFilter.isEmpty()) {
				getRows().forEach( row -> {
					Object valueRow = row.getValue(textField.getCaption());
					if (valueRow instanceof String) {
						String value = (String) valueRow;
						if (value.toUpperCase().contains(valueFilter.toUpperCase())) result.add(row);
					}
					if (valueRow instanceof Integer) {
						Integer value = (Integer) valueRow;
						if (value == Integer.valueOf(valueFilter)) result.add(row);
					}
					if (valueRow instanceof Double) {
						Double value = (Double) valueRow;
						if (value == Double.valueOf(valueFilter)) result.add(row);
					}
				});				
			}
		});
		return result;
	}

	/**
	 * The implementation of the methods is delegated to the class that inherits
	 */
	public abstract void init();
	
	
//	@Override
//	public List<T> findAll(List<ObjectFilter> collectionFilters) {
//		result = new ArrayList<T>();
//		
//		for (ObjectFilter textField : collectionFilters) {
//			Object value = textField.getValue();
//			getRows().forEach(row -> {
//				Object field = row.getValue(textField.getName());
//				if (field instanceof String) {
//					String f = (String) field;
//					String v = (String) value;
//					if (f.toUpperCase().contains(v.toUpperCase())) result.add(row);
//				}
//				if (field instanceof Integer) {
//					Integer f = (Integer) field;
//					Integer v = (Integer) value;
//					if (f == v) result.add(row);
//				}
//				if (field instanceof Double) {
//					Double f = (Double) field;
//					Double v = (Double) value;
//					if (f == v) result.add(row);
//				}
//			});				
//		};
//		return result;
//	}

}
