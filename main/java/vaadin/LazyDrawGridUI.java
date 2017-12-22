package vaadin;

import java.io.File;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.FooterRow;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;

import vaadin.service.MockDrawService;
import vaadin.utils.LazyDataModel;
import vaadin.vo.Draws;

@SpringUI(path = "/lazydraw-ui")
public class LazyDrawGridUI extends UI {

	private static final long serialVersionUID = -8027522037330142385L;
	private final MockDrawService service = new MockDrawService();
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		Grid<Draws> grid = new Grid<>(Draws.class);
		
		/** Bug Lazy Data */
		grid.setDataProvider(DataProvider.ofCollection(service.findAll(0, 40)));
		
		/** Only Pagination */
		grid.setDataProvider(LazyDataModel.fromService(service));
		
		/** Pagination and Sort */
		grid.setDataProvider(LazyDataModel.withSorting(service));

		/** Coustom style */
		style(grid);
		
		/* Layout */
		
		final VerticalLayout layout = new VerticalLayout();
		layout.addComponent(grid);
		setContent(layout);		
	}

	private void style(Grid<Draws> grid) {
		grid.setSizeFull(); 
		grid.setHeightByRows(15);
		/** 
		 * Column with Image
		 */
		String basepath = getClass().getResource("/static/images/").getPath();
		grid.addComponentColumn(d -> {
									FileResource resource = new FileResource(new File(basepath + d.getImage()));
									Image image = new Image(null, resource);
									image.setWidth("200px");
									image.setHeight("80px");
									return image;
		}).setId("resource").setCaption("Resource");
		
		/** 
		 * Column with Button
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
				footer.getCell("price"),
				footer.getCell("type"))
			  .setText("Total dresses " + service.count());
	}

}



