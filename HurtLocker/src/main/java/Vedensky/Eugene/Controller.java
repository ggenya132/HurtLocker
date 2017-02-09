package Vedensky.Eugene;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by eugenevendensky on 2/9/17.
 */




public class Controller {
    View view = new View();
    Parser parser = new Parser();
    public void coreBehavior() {
        String[] rawData = parser.splitStrings(parser.rawData);
        ArrayList<Grocery> groceries = parser.parseEntryToGrocery(rawData);
        HashMap<String, Double> groceryCollections = parser.countNumberOfInstancesOfPrice(groceries);
        view.displayItem();
        System.out.println("Milk");
        System.out.println(groceryCollections.get("Milk"));
        System.out.println("With prices : ");
        System.out.println("$1.23 " + groceryCollections.get("Milk Price 1.23") + " time(s) " + "$3.23 " + groceryCollections.get("Milk Price 3.23") + " time(s)");
        view.displayItem();
        System.out.println("Bread");
        System.out.println(groceryCollections.get("Bread"));
        System.out.println("With prices : ");
        System.out.println("$1.23 " + groceryCollections.get("Bread Price 1.23") + " time(s) ");
        view.displayItem();
        System.out.println("Cookie");
        System.out.println(groceryCollections.get("Cookie"));
        System.out.println("With prices : ");
        System.out.println("$2.25 " + groceryCollections.get("Cookie price") + " time(s) ");

    }

}

