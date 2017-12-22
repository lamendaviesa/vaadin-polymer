package vaadin.service;

import java.util.List;

import com.vaadin.ui.TextField;

import vaadin.vo.sort.ObjectSort;


/**
 * The ObjectService can be implemented by Mock Service, Csv Service, Jdbc Service. 
 * Object can be a Mock, a Csv, a Jdbc, etc.
 * @param <T>
 */
public interface ObjectService<T> {
	 List<T> findAll();
	 int count();
	 
	 List<T> findAll(int offset, int limit);
	 
	 List<T> findAllBySort(int offset, int limit, List<ObjectSort> sortOrders);
	 
	 List<T> findAllByFilter(int offset, int limit, List<TextField> collectionFilter);
}
