package gov.va.med.beer;

public class BeerProfile {

	private String beerName = "";
	private Float beerCost = -1f;
	private String beerType = "";
	private Integer	numberSold = -1;
	private Integer alcoholContent = -1; 
	private String color = "";
	
	public boolean isValid	= false;

	public void parseAndSetProperties(String currentLine) {

		// Safe parse
		final String safeParseBufferString = "|||||||||||||END";
		final String[] delimitedStringsArray = (currentLine + safeParseBufferString).split("\\|", -1);

		beerName = delimitedStringsArray[0];
		beerCost = parseBeerCost(delimitedStringsArray[1]);
		beerType = delimitedStringsArray[2];
		numberSold = parseNumberSold(delimitedStringsArray[3]);
		alcoholContent = parseAlcoholContent(delimitedStringsArray[4]);
		color = delimitedStringsArray[5];

		isValid = isValid(); 
	}
	
	private boolean isValid() {
		if(!beerName.trim().isEmpty() && !beerCost.equals(-1f) && !beerType.trim().isEmpty() && numberSold >=0 && alcoholContent >=0) {
			return true;
		}
		return false;
	}
	
	private Float parseBeerCost(String value) {
		if(tryParseFloat(value)) {
			return Float.parseFloat(value);
		}
		return this.beerCost;		
	}
	
	private Integer parseNumberSold(String value) {
		if(tryParseInt(value)) {
			return Integer.parseInt(value);
		}
		return this.numberSold;
	}
	
	private Integer parseAlcoholContent(String value) {
		if(tryParseInt(value)) {
			return Integer.parseInt(value);
		}
		return this.alcoholContent;
	}

	private boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean tryParseFloat(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public String getBeerName() {
		return this.beerName;
	}
	public Float getBeerCost() {
		return this.beerCost;
	}
	public String getBeerType() {
		return this.beerType;
	}
	public Integer getNumberSold() {
		return this.numberSold;
	}
	public Integer getAlcoholContent() {
		return this.alcoholContent;
	}
	public String getColor() {
		return this.color;
	}



}