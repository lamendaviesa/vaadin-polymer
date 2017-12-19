package vaadin.service;

import java.util.List;

import com.vaadin.ui.TextField;

import vaadin.vo.ObjectSort;


/**
 * The ObjectService can be implemented by Mock Service, Csv Service, Jdbc Service. 
 * Object can be a Mock, a Csv, a Jdbc, etc.
 * @param <T>
 */
public interface ObjectService<T> {
	 List<T> findAll();
	 List<T> findAll(int offset, int limit);
	 List<T> findAll(int offset, int limit, List<ObjectSort> sortOrders);
	 List<T> findAll(int offset, int limit, TextField filter);
	 List<T> findAll(List<TextField> collectionFilters); //TODO better List<T> findAll(List<ObjectFilter> collectionFilters);
	 int count();
	 public int count(int offset, int limit);
	 public int count(int offset, int limit,  List<ObjectSort> sortOrders); 
	 int count(int offset, int limit, TextField filter);
	 int count(List<TextField> collectionFilters);
}
