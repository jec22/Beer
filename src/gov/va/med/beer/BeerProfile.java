package gov.va.med.beer;

public class BeerProfile {

	public final String getBeerName() {
		return beerName;
	}

	public final Float getBeerCost() {
		return beerCost;
	}

	public final String getBeerType() {
		return beerType;
	}

	public final Integer getNumberSold() {
		return numberSold;
	}
	public final Integer getAlcoholContent() {
		return alcoholContent;
	}
	

	public final String getColor() {
		return color;
	}

	private String		beerName;
	private Float		beerCost;
	private String		beerType;
	private Integer	numberSold;
	public boolean		isValid	= false;
	private String color;
	private Integer alcoholContent; 

	public void parseAndSetProperties(String currentLine) {

		final String safeParseBufferString = "|||||||||||||END";

		// add safeParseBufferString to input, then delimit and keep empty-strings
		final String[] delimitedStringsArray = (currentLine + safeParseBufferString).split("\\|", -1);

		//get values from array (in order by array element)
		String beerNameFromSource = delimitedStringsArray[0];

		Float beerCostFromSource = -1f; //initialize with error indicator
		if (tryParseFloat(delimitedStringsArray[1])) {
			// at this point, it is safe to parse 
			beerCostFromSource = Float.parseFloat(delimitedStringsArray[1]);
		}

		String beerTypeFromSource = delimitedStringsArray[2];

		Integer numberSoldFromSource = -1;
		if (tryParseInt(delimitedStringsArray[3])) {
			// at this point, it is safe to parse 
			numberSoldFromSource = Integer.parseInt(delimitedStringsArray[3]);
		}
		Integer beerAlcoholContent = 0;
		if(tryParseInt(delimitedStringsArray[4])) {
			
			beerAlcoholContent = Integer.parseInt(delimitedStringsArray[4]);
			
		}
		//validation of the input:

		//check if variables are empty or null
		if (beerNameFromSource.isEmpty() || beerNameFromSource.equals(null) || beerCostFromSource.equals(null) || numberSoldFromSource.equals(null)
				|| beerTypeFromSource.isEmpty() || beerTypeFromSource.equals(null)) {

			isValid = false;
		} else if ((beerCostFromSource < 0 || numberSoldFromSource < 0)) { //must be positive
			isValid = false;
		} else if (beerAlcoholContent < 0){
			isValid = false;
		} else {
			// at this point, then we passed all the the above, so we return true
			isValid = true;

			// set internal properties since it is valid
			beerName = beerNameFromSource;
			beerCost = beerCostFromSource;
			beerType = beerTypeFromSource;
			numberSold = numberSoldFromSource;
			alcoholContent = beerAlcoholContent;
			color = delimitedStringsArray[5];
		}
	}

	//custom functions
	private boolean tryParseInt(String value) {
		//check to see if integer can be parsed from a string

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
		//check to see if float can be parsed from a string
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

}