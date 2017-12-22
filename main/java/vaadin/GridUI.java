package vaadin;

import java.util.Arrays;
import java.util.List;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import vaadin.vo.Person;

@SpringUI(path = "/grid")
@Title("grid")
public class GridUI extends UI {

	private static final long serialVersionUID = 3210449234965444681L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		String var = vaadinRequest.getParameter("var");

		final VerticalLayout layout = new VerticalLayout();
		
		List<Person> people = Arrays.asList(
				new Person("Raquel", 30),
				new Person("Miguel", 36),
				new Person("Mata", 30), 
				new Person("Mendia", 30),
				new Person("Mikel", 36));
		
		Grid<Person> grid = new Grid<>();
		grid.setItems(people);
		grid.addColumn(Person::getName).setCaption("Name");
		grid.addColumn(Person::getAge).setCaption("Age");
		
		layout.addComponent(grid);
		
		layout.addComponent((Component) new Label("Parameter: " + var));
		setContent(layout);		
	}
}



