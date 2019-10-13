public class MarketFactory {
	public static Magazin createMagazin(String type) {
		switch(type) {
		case "MiniMarket":
			return new MiniMarket();
		case "MediumMarket":
			return new MediumMarket();
		case "HyperMarket":
			return new HyperMarket();
		}
		throw new IllegalArgumentException("The market type " + 
				type + " is not recognized.");
	}
}
