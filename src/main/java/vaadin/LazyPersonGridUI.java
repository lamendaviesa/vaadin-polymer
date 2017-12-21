package vaadin;

import com.vaadin.data.HasValue;
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
import vaadin.vo.Person;

@SpringUI(path = "/lazyperson-ui")
public class LazyPersonGridUI extends UI {
	
	private static final long serialVersionUID = 6635437980453626866L;

	private final MockPersonService service = new MockPersonService();
	private TextField filterName = new TextField("name");
	private TextField filterAge = new TextField("age");

	public static class PersonFilter {
		public final String name;
		public final Integer age;

		public PersonFilter(String name, Integer age) {
			this.name = name;
			this.age = age;
		}
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Grid<Person> grid = new Grid<>(Person.class);
		grid.setSizeFull();
		grid.setHeightByRows(15);

		DataProvider<Person, PersonFilter> dataProvider = DataProvider.fromFilteringCallbacks(
				query -> service.find(
                        query.getOffset(),
                        query.getLimit(),
						filterName.getValue().trim().isEmpty() ? null : filterName.getValue().trim(),
						filterAge.getValue().trim().isEmpty() ? null : Integer.valueOf(filterAge.getValue().trim())
                ).stream(),
				query -> service.count(
						filterName.getValue().trim().isEmpty() ? null : filterName.getValue().trim(),
						filterAge.getValue().trim().isEmpty() ? null : Integer.valueOf(filterAge.getValue().trim())
                )
		);

		/** Bug Lazy Data */
		grid.setDataProvider(dataProvider);
		
		/** Only Pagination */
		//grid.setDataProvider(LazyDataModel.fromService(service));
		/** Pagination and Sorting */
		//grid.setDataProvider(LazyDataModel.withSorting(service));

		/** Pagination and Filtering */
		//Name
		HeaderCell nameCell = grid.getHeaderRow(0).getCell("name");
		filterName.setWidth("80%"); //style
		filterName.setPlaceholder("Name");
		nameCell.setComponent(filterName);


		HasValue.ValueChangeListener<String> filterValueChangeListener = event -> {
			Notification.show(filterName.getCaption() + " = " + filterName.getValue() + ". "
					+ filterAge.getCaption() + " = " + filterAge.getValue());
			dataProvider.refreshAll();
		};

		filterName.addValueChangeListener(filterValueChangeListener);
		
		//TODO
		// Add more filter
		//Age
		HeaderCell age = grid.getHeaderRow(0).getCell("age");
		filterAge.setWidth("80%");
		filterAge.setPlaceholder("Age");
		age.setComponent(filterAge);
		
		filterAge.addValueChangeListener(filterValueChangeListener);
		
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


