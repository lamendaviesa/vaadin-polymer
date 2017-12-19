package vaadin.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.TextField;

import vaadin.service.ObjectService;
import vaadin.vo.ObjectFilter;
import vaadin.vo.ObjectSort;

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
				return service.count(offset, offset + limit);
			}
		);
		return dataProvider;
	}
	
	/**
	 *  Pagination and Sorting
	 *
	 * FrameWork >< Element 
	 * query == opts
	 * query.sortOrders (List<SortOrdes>) == opts.sortOrders(array of Object)
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
				return service.findAll(offset, offset + limit, paramsOrders).stream();
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
				return service.count(offset, offset + limit, paramsOrders); 
			}
		);
		return dataProvider;
	}

	/**
	 *  Pagination and Filtering
	 */
	public static <T> DataProvider<T, String> withFilter(ObjectService<T> service, TextField textFilter) {	
		DataProvider<T, String> dataProvider = DataProvider.fromFilteringCallbacks(
			query -> {
				final int offset = query.getOffset();
				final int limit = query.getLimit();
				Optional<String> filter = query.getFilter();
				filter.isPresent();
				//MessageUtil.filtering(offset, limit, textFilter.getValue());
				return service.findAll(offset, offset + limit, textFilter).stream(); /** Class Stream not permited in Android SDK API less than 24  */
			}, 
			query -> {
				final int offset = query.getOffset();
				final int limit = query.getLimit();
				return service.count(offset, offset + limit, textFilter);
			}
		);
		return dataProvider;
	}
	
	/**
	 * Pagination and Filter by Collection Of Filter 
	 */
	@Deprecated
	public static <T> DataProvider<T, String> withColletionsOfFilter(ObjectService<T> service, List<TextField> collectionFilters) {
		DataProvider<T, String> dataProvider = DataProvider.fromFilteringCallbacks(
			query -> {
				return service.findAll(collectionFilters).stream();
			}, 
			query -> {
				return service.count(collectionFilters);
			}
		);
		return dataProvider;
	}
	
	/**
	 * Pagination and Filter by ObjectFilter
	 */
	@Deprecated
	public static <T> DataProvider<T, ObjectFilter> withObjectFilter(ObjectService<T> service, Query<T, ObjectFilter> query) {
		DataProvider<T, ObjectFilter> dataProvider = DataProvider.fromFilteringCallbacks(
			q -> {
				query.getFilter().orElse(null);
				return service.findAll().stream();
			}, 
			q -> {
				return service.count();
			}	
		);
		return dataProvider;
	}
}
