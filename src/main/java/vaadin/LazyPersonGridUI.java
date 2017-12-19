package vaadin;

import java.util.Arrays;
import java.util.List;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderCell;

import vaadin.service.MockPersonService;
import vaadin.utils.LazyDataModel;
import vaadin.vo.Person;

@SpringUI(path = "/lazyperson")
public class LazyPersonGridUI extends UI {
	
	private static final long serialVersionUID = 6635437980453626866L;

	private final MockPersonService service = new MockPersonService();
	private TextField filterName = new TextField("name");
	private TextField filterAge = new TextField("age");
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Grid<Person> grid = new Grid<>(Person.class);
		grid.setSizeFull();
		grid.setHeightByRows(15);

		/** Bug Lazy Data */
		grid.setDataProvider(DataProvider.ofCollection(service.findAll(0, 40)));
		
		/** Only Pagination */
		grid.setDataProvider(LazyDataModel.fromService(service));
		/** Pagination and Sorting */
		grid.setDataProvider(LazyDataModel.withSorting(service));

		/** Pagination and Filtering */
		//Name
		HeaderCell name = grid.getHeaderRow(0).getCell("name");
		filterName.setWidth("80%"); //style
		filterName.setPlaceholder("Name");
		name.setComponent(filterName);
		
		filterName.addValueChangeListener(event -> {
			Notification.show(filterName.getCaption() + " = " + filterName.getValue());
			grid.setDataProvider(LazyDataModel.withFilter(service, filterName));
		});
		
		//TODO
		// Add more filter
		//Age
		HeaderCell age = grid.getHeaderRow(0).getCell("age");
		filterAge.setWidth("80%");
		filterAge.setPlaceholder("Age");
		age.setComponent(filterAge);
		
		filterAge.addValueChangeListener(event -> {
			Notification.show(filterAge.getCaption() + " = " + filterAge.getValue());
			grid.setDataProvider(LazyDataModel.withFilter(service, filterAge));
		});
		
		//Event Listeners
//		List<TextField> collectionFilters = Arrays.asList(filterName, filterAge);
//		
//		collectionFilters.forEach(filter -> {
//			filter.addValueChangeListener(event -> {
//				Notification.show(filter.getCaption() + " = " + filter.getValue());
//				grid.setDataProvider(LazyDataModel.withColletionsOfFilter(service, collectionFilters));
//			});
//		});
		
		
		/** Layout */
		final VerticalLayout layout = new VerticalLayout();
		layout.addComponent(grid);		
		setContent(layout);		
	}
	
}

//ObjectFilter filter = new ObjectFilter(filterAge.getCaption(), filterAge.getValue());
//Query<Person, ObjectFilter> query = new Query<Person, ObjectFilter>(filter);


