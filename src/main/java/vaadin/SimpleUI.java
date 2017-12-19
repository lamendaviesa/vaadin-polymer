package vaadin;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SpringUI(path = "/ui")
@Title("ui")
public class SimpleUI extends UI {

	private static final long serialVersionUID = -5594007454249151852L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		setContent(new Label("SIMPLE UI!"));
	}
}
