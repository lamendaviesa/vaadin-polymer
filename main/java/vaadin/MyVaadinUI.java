package vaadin;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/vaadin")
@Title("vaadin")
public class MyVaadinUI extends UI {
	
	private static final long serialVersionUID = -821982280096077796L;
	private VerticalLayout layout;
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		layout = new VerticalLayout();
	    layout.addComponents(new Label("VAADIN!")); 
	    
	    final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
	}
}
