package vaadin.predicates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.vaadin.ui.TextField;

import vaadin.vo.GridElement;

public class TPredicates {

	
	public static <T extends GridElement> Predicate<T> predicate(TextField filter) {
		return row -> isRowCanditate(row, filter);
	}
	
	public static <T extends GridElement> List<Predicate<T>> predicates(List<TextField> collectionFilter) {
		List<Predicate<T>> predicates = new ArrayList<>();
		collectionFilter.forEach(
			filter -> { 
				predicates.add(predicate(filter));
		});
		return predicates;
	}

	public static <T extends GridElement> boolean isRowCanditate(T row, TextField filter) {
		Object valueRow = row.getValue(filter.getCaption());
		boolean filterValue = filter.getValue().trim().isEmpty();
		
		if (valueRow instanceof String) {
			String value = (String) valueRow;
			return filterValue || value.toUpperCase().contains(filter.getValue().trim().toUpperCase());
		} else if (valueRow instanceof Integer) {
			Integer value = (Integer) valueRow;
			return filterValue || value == Integer.valueOf(filter.getValue().trim());
		} else if (valueRow instanceof Double) {
			Double value = (Double) valueRow;
			return filterValue || value == Double.valueOf(filter.getValue().trim());
		} else return false;
	}
}
