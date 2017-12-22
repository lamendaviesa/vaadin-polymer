package vaadin;

import java.util.Arrays;

import com.vaadin.data.HasValue;
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

@SpringUI(path = "/lazyperson-ui")
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
		filterHeaderCell(grid);

		/** Only Pagination */
		grid.setDataProvider(LazyDataModel.fromService(service));
		
		/** Pagination and Sorting */
		grid.setDataProvider(LazyDataModel.withSorting(service));
		

		/** Pagination and Filtering */
		HasValue.ValueChangeListener<String> filterValueChangeListener = event -> {
			Notification.show(filterName.getCaption() + " = " + filterName.getValue() + "\n" + filterAge.getCaption() + " = " + filterAge.getValue());
			grid.setDataProvider(LazyDataModel.withFilter(service, Arrays.asList(filterName, filterAge)));
		};

		filterName.addValueChangeListener(filterValueChangeListener);
		filterAge.addValueChangeListener(filterValueChangeListener);
		
		/** Layout */
		final VerticalLayout layout = new VerticalLayout();
		layout.addComponent(grid);		
		setContent(layout);		
	}

	private void filterHeaderCell(Grid<Person> grid) {
		//Name
		HeaderCell name = grid.getHeaderRow(0).getCell("name");
		filterName.setWidth("80%"); //style
		filterName.setPlaceholder("Name");
		name.setComponent(filterName);
		//Age
		HeaderCell age = grid.getHeaderRow(0).getCell("age");
		filterAge.setWidth("80%");
		filterAge.setPlaceholder("Age");
		age.setComponent(filterAge);
	}
	
}


