package gov.va.med.beer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class BeerMain {

	public static void main(String[] args) throws IOException {
		BeerMain beerMain = new BeerMain();
		beerMain.addToMap("src/beer.txt"); //call to process file and add entries to beerMap
		
		
	}

	public void addToMap(String inputFile) throws IOException {

		final HashMap<String, ArrayList<BeerProfile>> beerMap = new HashMap<String, ArrayList<BeerProfile>>();

		// //open new reader for next while
		FileReader input = new FileReader(inputFile);
		BufferedReader reader = new BufferedReader(input);

		String currentLine;

		// loop through file and add to carMap
		try {
			while ((currentLine = reader.readLine()) != null) {
				BeerProfile beerProfile = new BeerProfile();

				beerProfile.parseAndSetProperties(currentLine); //input currentLine				

				if (beerProfile.isValid) {

					//ArrayList for values in map
					ArrayList<BeerProfile> beerList = new ArrayList<BeerProfile>();

					// check to see if ArrayList exists in HashMap for present key (getDoorCount)
					if (beerMap.containsKey(beerProfile.getBeerType())) {
						beerList = beerMap.get(beerProfile.getBeerType()); //get current ArrayList						
					}

					beerList.add(beerProfile); //add current object to ArrayList
					beerMap.put(beerProfile.getBeerType(), beerList); //add ArrayList to map

				} else {
					System.out.println("Invalid line: " + currentLine + "\n");
				}
			}
		} catch (IOException e) {
			//catch - send stack trace
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//		printCarsByDoorCount(beerMap); //print results
		printBeerByTypes(beerMap);

		System.out.println("");

		reader.close(); //close reader
	}

	private void printBeerByTypes(HashMap<String, ArrayList<BeerProfile>> beerMap) {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US); // used for formatting to US currency

		//variables to sum cost and number of beers sold

		//add up cost and count per type of beer
		Integer beerCount = 0;
		float beerCost = 0f;
		Integer alcoholContent = 0;
		
		//total combined count and cost of beer
		Integer totCount = 0;
		float totRevenue = 0f;
		//creates Iterator to navigate through our map.
		Iterator<Map.Entry<String, ArrayList<BeerProfile>>> it = beerMap.entrySet().iterator();
		
		while(it.hasNext()) {
			//get ArrayList for key
			ArrayList<BeerProfile> beerProfileList = new ArrayList<BeerProfile>();
			Map.Entry<String, ArrayList<BeerProfile>> entry = it.next();
			
			beerProfileList = entry.getValue();

			System.out.println((entry.getKey() + " BEERS:").toUpperCase()); //print heading

			for (BeerProfile profileElement : beerProfileList) {

				//add up totals while looping for each beer type
				beerCost += profileElement.getBeerCost();
				beerCount += profileElement.getNumberSold();
				alcoholContent = profileElement.getAlcoholContent();
	
				System.out.println("* "+profileElement.getBeerName()+" - Color: "+profileElement.getColor() + " - Alcohol Content: " + alcoholContent + "%");
				
			}
			//add to totals
			totCount += beerCount;
			totRevenue += (beerCount * beerCost);	

			//reset for next type
			beerCount = 0;
			beerCost = 0;

		}

		//print totals for types
		System.out.println("* Total count of beer sold in entire list: " + totCount);
		System.out.println("* Total revenue of beer sold from entire list: " + currencyFormat.format(totRevenue));

	}

}