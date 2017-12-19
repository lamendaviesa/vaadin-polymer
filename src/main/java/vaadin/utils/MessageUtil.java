package vaadin.utils;

import java.util.List;

import com.vaadin.data.provider.QuerySortOrder;

public class MessageUtil {

	public static void sorting(int offset, int limit, List<QuerySortOrder> sortOrders) {
		int fromIndex = offset;
		int toIndex = offset + limit;
		System.out.println("Orders | Offset | Limit " + sortOrders + " | " + offset + " | "  + limit);
		System.out.println("[ From, To ]" + "[ " + fromIndex + ", " + toIndex + " ]");
	}

	public static void filtering(int offset, int limit, String filter) {
		int fromIndex = offset;
		int toIndex = offset + limit;
		System.out.println("[ From, To ]" + "[ " + fromIndex + ", " + toIndex + " ]");
		if (filter != null) System.out.println(" Filter " + filter.toUpperCase());
	}

	public static void sqlExpression(String expression) {
		System.out.println(expression);
	}
}
