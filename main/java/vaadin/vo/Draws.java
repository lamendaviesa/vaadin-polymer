package vaadin.vo;

public class Draws extends GridElement {
	private String image;
	private String type;
	private double price;
	private String value3;
	private String value4;
	private String value5;
	private String value6;
	private String value7;
	private String value8;
	private String value9;
	private String value10;
	private String value11;
	private String value12;
	private String value13;
	private String value14;
	private String value15;
	private String value16;
	private String value17;
	private String value18;
	private String value19;
	private String value20;
	
	@Override
	public Object getValue(String fieldName) {
		switch (fieldName) {
		case "image":
			return this.image;
		case "type":
			return this.type;
		case "price":
			return this.price;
		case "value3":
			return this.value3;
		case "value4":
			return this.value4;
		case "value5":
			return this.value5;
		case "value6":
			return this.value6;
		case "value7":
			return this.value7;
		//TODO	
		default:
			return new Object();
		}	
	}
	
	public Draws(String image, String type, double price) {
		this.image = image;
		this.type = type;
		this.price = price;
		this.value3 = null;
		this.value4 = "Italia";
		this.value5 = null;
		this.value6 = null;
		this.value7 = null;
		this.value8 = null;
		this.value9 = null;
		this.value10 = null;
		this.value11 = null;
		this.value13 = null;
		this.value14 = null;
		this.value15 = null;
		this.value16 = null;
		this.value17 = null;
		this.value18 = null;
		this.value19 = null;
		this.value20 = null;
	}
	
	public Draws(String image, String type, double price, String value4) {
		this.image = image;
		this.type = type;
		this.price = price;
		this.value3 = null;
		this.value4 = value4;
		this.value5 = null;
		this.value6 = null;
		this.value7 = null;
		this.value8 = null;
		this.value9 = null;
		this.value10 = null;
		this.value11 = null;
		this.value13 = null;
		this.value14 = null;
		this.value15 = null;
		this.value16 = null;
		this.value17 = null;
		this.value18 = null;
		this.value19 = null;
		this.value20 = null;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	public String getValue6() {
		return value6;
	}

	public void setValue6(String value6) {
		this.value6 = value6;
	}

	public String getValue7() {
		return value7;
	}

	public void setValue7(String value7) {
		this.value7 = value7;
	}

	public String getValue8() {
		return value8;
	}

	public void setValue8(String value8) {
		this.value8 = value8;
	}

	public String getValue9() {
		return value9;
	}

	public void setValue9(String value9) {
		this.value9 = value9;
	}

	public String getValue10() {
		return value10;
	}

	public void setValue10(String value10) {
		this.value10 = value10;
	}

	public String getValue11() {
		return value11;
	}

	public void setValue11(String value11) {
		this.value11 = value11;
	}

	public String getValue12() {
		return value12;
	}

	public void setValue12(String value12) {
		this.value12 = value12;
	}

	public String getValue13() {
		return value13;
	}

	public void setValue13(String value13) {
		this.value13 = value13;
	}

	public String getValue14() {
		return value14;
	}

	public void setValue14(String value14) {
		this.value14 = value14;
	}

	public String getValue15() {
		return value15;
	}

	public void setValue15(String value15) {
		this.value15 = value15;
	}

	public String getValue16() {
		return value16;
	}

	public void setValue16(String value16) {
		this.value16 = value16;
	}

	public String getValue17() {
		return value17;
	}

	public void setValue17(String value17) {
		this.value17 = value17;
	}

	public String getValue18() {
		return value18;
	}

	public void setValue18(String value18) {
		this.value18 = value18;
	}

	public String getValue19() {
		return value19;
	}

	public void setValue19(String value19) {
		this.value19 = value19;
	}

	public String getValue20() {
		return value20;
	}

	public void setValue20(String value20) {
		this.value20 = value20;
	}

}
