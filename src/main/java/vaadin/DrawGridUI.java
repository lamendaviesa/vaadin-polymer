package vaadin;

import java.io.File;
import java.util.List;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.FooterRow;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;

import vaadin.service.MockDrawService;
import vaadin.vo.Draws;

@SpringUI(path = "/draw")
public class DrawGridUI extends UI {

	private static final long serialVersionUID = 1586944997126072048L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		String var = vaadinRequest.getParameter("var");

		final VerticalLayout layout = new VerticalLayout();
		
		Grid<Draws> grid = new Grid<>(Draws.class);
		grid.setSizeFull(); //style
		grid.setHeightByRows(15);;
		
		/*
		 *		Data 
		 */
		List<Draws> draws = new MockDrawService().findAll(0, 8);
		
		/** LOAD THE GRID  
		* Exampple 1
		grid.setItems(dresses);
		*/
		
		/** Exampple 2
		grid.setDataProvider(DataProvider.ofCollection(dresses));
		 */
		/** Exampple 3 */ 
		ListDataProvider<Draws> dataProvider = new ListDataProvider<>(draws); 
		grid.setDataProvider(dataProvider);
		
		
		/*
		 *		Define some columns diferents
		 */
		
		
		/** 
		 * Column with Image
		 */
		//	Example 1
		/**
		Column<Dress, Resource> imageColumn = grid.addColumn(d -> new ExternalResource( "http://localhost:8080/images/rose.png")); //+ d.getImage()));
		imageColumn.setRenderer(new ImageRenderer<>());
		imageColumn.setId("resource").setCaption("Resource").setExpandRatio(1);
		*/
		//Example 2
		String basepath = getClass().getResource("/static/images/").getPath();
		grid.addComponentColumn(d -> {
									FileResource resource = new FileResource(new File(basepath + d.getImage()));
									Image image = new Image(null, resource);
									image.setWidth("100px");
									image.setHeight("180px");
									return image;
		}).setId("resource").setCaption("Resource");
		
		/** 
		 * Column with a link inside the iframe <base target="_parent">
		 */
		// Exampple 1
		grid.addComponentColumn(d -> {
			Link link = new Link(null, new ExternalResource("http://raquel-pc:8080/figure"));
			link.setTargetName("_parent");
			link.setIcon(VaadinIcons.EYE);
			return link;
		}).setId("details").setCaption("Details");
		
		// Exampple 2
		grid.addComponentColumn(d -> {
			Button button = new Button();
			button.setIcon(VaadinIcons.ALARM);
			return button;
		}).setId("icon").setCaption("Icon");
		
		
		/** 
		 * Change HeaderCell for a component
		 */
		// 1- component == icon
		HeaderCell value3 = grid.getHeaderRow(0).getCell("value3");
		Label label = new Label(VaadinIcons.AIRPLANE.getHtml(), ContentMode.HTML);
		value3.setComponent(label); 
		
		// 2- component == filter
		HeaderCell value4 = grid.getHeaderRow(0).getCell("value4");
		TextField filter = new TextField();
		filter.setWidth("100%"); //style
		filter.setPlaceholder("Value4");
		value4.setComponent(filter);
		
		//The performance of the filter 
		performanceFilter(filter, dataProvider);
		
		
		/**
		* Order of Columns
		*/
		grid.setColumnOrder("image", "type", "price", 
							"resource", "details", "icon",
							"value3", "value4", "value5", 
							"value6", "value7", "value8", 
							"value9", "value10", "value11",
							"value12", "value13", "value14",
							"value15", "value16", "value17",
							"value18", "value19", "value20");
		
		/**
		* Columns fixed
		*/
		grid.setFrozenColumnCount(3);

		
		/**
		* Column Groups
		*/
		HeaderRow groupingHeader = grid.prependHeaderRow();
		groupingHeader.join(groupingHeader.getCell("type"), 
							groupingHeader.getCell("price"))
					 	.setText("Characteristic");
		groupingHeader.join(groupingHeader.getCell("value3"),
							groupingHeader.getCell("value4"),
							groupingHeader.getCell("value5"))
					 	.setText("ColumnGroup2");	
		groupingHeader.join(groupingHeader.getCell("value6"),
							groupingHeader.getCell("value7"),
							groupingHeader.getCell("value8"))
						.setText("ColumnGroup3");
		groupingHeader.join(groupingHeader.getCell("value9"),
							groupingHeader.getCell("value10"),
							groupingHeader.getCell("value11"))
						.setText("ColumnGroup4");
		groupingHeader.join(groupingHeader.getCell("value12"),
							groupingHeader.getCell("value13"),
							groupingHeader.getCell("value14"))
						.setText("ColumnGroup5");
		groupingHeader.join(groupingHeader.getCell("value15"),
							groupingHeader.getCell("value16"),
							groupingHeader.getCell("value17"))
						.setText("ColumnGroup6");
		groupingHeader.join(groupingHeader.getCell("value18"),
							groupingHeader.getCell("value19"),
							groupingHeader.getCell("value20"))
						.setText("ColumnGroup7");
		
		/**
		* Footer Groups
		*/
		FooterRow footer = grid.appendFooterRow();
		footer.join(footer.getCell("image"),
				footer.getCell("type"),
				footer.getCell("price"))
			  .setText("Total dresses " + draws.size());

		/*
		 *		Layout
		 */
		
		layout.addComponent(grid);		

		if (var != null) layout.addComponent((Component) new Label("Parameter: " + var));
		
		//Show the image in the application
		// 1 - File resource
		/**
		FileResource resource = new FileResource(new File(basepath + "mini_dress.png"));
		Image image = new Image("Image from file", resource);
		image.setWidth("200px");
		image.setHeight("80px");
		layout.addComponent(image);
		*/
		// 2 - External resource
		/**
		Resource r = new ExternalResource("http://localhost:8080/images/rose.png");
		layout.addComponent(new Image(null, r));
		*/
		setContent(layout);		
	}

	/**
	 * Performance of the Value4 Filter
	 * @param filter
	 * @param dataProvider
	 */
	private void performanceFilter(TextField filter, ListDataProvider<Draws> dataProvider) {
		filter.addValueChangeListener(event -> {
			Notification.show("Value4 " + filter.getValue());
			dataProvider.setFilter(Draws::getValue4, value4 -> {
				if (value4 == null) {
					return false;
				}
				if(event.getValue() == null) {
					return true;
				}
				String value4Upper = value4.toUpperCase();
				String filterUpper = event.getValue().toString().toUpperCase();
				return value4Upper.contains(filterUpper);
			});
		});
	}
	
}



