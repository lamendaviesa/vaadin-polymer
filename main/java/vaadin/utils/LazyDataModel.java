package vaadin.utils;


import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.TextField;

import vaadin.service.ObjectService;
import vaadin.vo.filter.ObjectFilter;
import vaadin.vo.sort.ObjectSort;

public class LazyDataModel {
	
	/**  
	 * Only Pagination
	 */
	public static <T> DataProvider<T, Void> fromService(ObjectService<T> service) {
		DataProvider<T, Void> dataProvider = DataProvider.fromCallbacks(
			query -> {
				int offset = query.getOffset();
				int limit = query.getLimit(); 
				return service.findAll(offset, offset + limit).stream(); 
			}, 
			query -> {
				int offset = query.getOffset();
				int limit = query.getLimit(); 
				return service.findAll(offset, offset + limit).size();
			}
		);
		return dataProvider;
	}
	
	/**
	 *  Pagination and Sorting
	 *
	 * FrameWork >< Element 
	 * query == opts
	 * query.sortOrders (List<QuerySortOrder>) == opts.sortOrders(array of Object)
	 * sort.getSorted() == sort.path
	 * sort.getDirection() == sort.direction
	 * query.filters == opts.filters
	 */
	public static <T> DataProvider<T, Void> withSorting(ObjectService<T> service) {
		DataProvider<T, Void> dataProvider = DataProvider.fromCallbacks(
			query -> {
				int offset = query.getOffset();
				int limit = query.getLimit(); 
				List<QuerySortOrder> sortOrders = query.getSortOrders();					
				List<ObjectSort> paramsOrders = new ArrayList<>();
				sortOrders.forEach(sort -> {
					ObjectSort personSort = new ObjectSort(sort.getSorted(), sort.getDirection() == SortDirection.DESCENDING);
					paramsOrders.add(personSort);
				});
				//MessageUtil.sorting(offset, limit, sortOrders);
				return service.findAllBySort(offset, offset + limit, paramsOrders).stream();
			}, 
			query -> {
				int offset = query.getOffset();
				int limit = query.getLimit(); 
				List<QuerySortOrder> sortOrders = query.getSortOrders();					
				List<ObjectSort> paramsOrders = new ArrayList<>();
				sortOrders.forEach(sort -> {
					ObjectSort personSort = new ObjectSort(sort.getSorted(), sort.getDirection() == SortDirection.DESCENDING);
					paramsOrders.add(personSort);
				});
				return service.findAllBySort(offset, offset + limit, paramsOrders).size(); 
			}
		);
		return dataProvider;
	}

	/**
	 * Pagination and Filter by Collection Of Filter 
	 */
	public static <T> DataProvider<T, ObjectFilter> withFilter(ObjectService<T> service, List<TextField> collectionFilters) {
		DataProvider<T, ObjectFilter> dataProvider = DataProvider.fromFilteringCallbacks(
			query -> {
				final int offset = query.getOffset();
				final int limit = query.getLimit();
				return service.findAllByFilter(offset,  offset + limit, collectionFilters).stream();
			}, 
			query -> {
				final int offset = query.getOffset();
				final int limit = query.getLimit();
				return service.findAllByFilter(offset,  offset + limit, collectionFilters).size();
			}	
		);
		return dataProvider;
	}
}
