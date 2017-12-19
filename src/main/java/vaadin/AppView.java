package vaadin;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path="/app")
public class AppView extends UI {

	private static final long serialVersionUID = -6309581226491217261L;

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setContent(layout);
		
		String path = getClass().getResource("/templates/app_polymer.html").getPath();
		File file = new File(path);
		CustomLayout content;
		try {
			content = new CustomLayout(new FileInputStream(file));
			layout.addComponent(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
