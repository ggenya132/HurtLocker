package Vedensky.Eugene;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eugenevendensky on 2/9/17.
 */
public class Parser {

    String rawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##" +
            "naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##" +
            "NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##" +
            "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##" +
            "naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##" +
            "naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##" +
            "naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##" +
            "naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##" +
            "NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##" +
            "naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##" +
            "naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##" +
            "naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##" +
            "NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##" +
            "naMe:;price:3.23;type:Food;expiration:1/04/2016##" +
            "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##" +
            "naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##" +
            "NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##" +
            "naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##" +
            "naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##" +
            "naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##" +
            "naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##" +
            "naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##" +
            "NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##" +
            "naMe:MilK;priCe:;type:Food;expiration:4/25/2016##" +
            "naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##" +
            "naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##" +
            "NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##" +
            "naMe:;price:3.23;type:Food^expiration:1/04/2016##";

    public String[] splitStrings(String rawData){

        String[] splitData = rawData.split("##");
        return splitData;
    }

    public String getFoodMatches(String food) throws CustomException {
        Pattern p = Pattern.compile("\\b[mMbBcCaA]\\w+");
        Matcher matcher = p.matcher(food);
        String match = null;
        if (matcher.find()) {
            match = matcher.group();
            match = match.toLowerCase();
            if (match != null) {
                Pattern pattern = Pattern.compile("[cC]..[kK][iI][eE][sS]");
                Matcher newMatcher = pattern.matcher(food);
                if (newMatcher.find()) {
                    String cookieMatch = "cookie";
                    return cookieMatch;
                }


            }
            return match;
        }

        else throw new CustomException();
    }

    public Double matchPrice(String food) throws CustomException {
        Pattern p = Pattern.compile("\\b\\d+\\.\\d+");
        Matcher matcher = p.matcher(food);
        String match = null;
        if (matcher.find()) {
            match = matcher.group();
            double price = Double.parseDouble(match);
            return price;
    }
    else throw new CustomException();
    }

    public ArrayList<Grocery> parseEntryToGrocery(String[] rawData) {
        ArrayList<Grocery> groceries = new ArrayList<Grocery>();
        Grocery grocery = null;

            for (String s : rawData) {
                try {
                    String foodType = getFoodMatches(s);
                    double price = matchPrice(s);
                    grocery = new Grocery(foodType, price);
                    groceries.add(grocery);
                }
                catch (CustomException e){}
            }

        return groceries;
        }

    public HashMap<String, Double> countNumberOfInstancesOfPrice(ArrayList<Grocery> groceries){
        HashMap<String, Double> newHash = new HashMap<String, Double>();
        Double milkPrice123 = 0.0;
        Double milkPrice323 = 0.0;
        Double breakprice123 = 0.0;
        Double cookieprice = 0.0;

        Double applePrice23 = 0.0;
        Double applePrice25 = 0.0;
        Double milkCount = 0.0;
        Double cookiecount = 0.0;
        Double applecount = 0.0;
        Double breadcount = 0.0;
        for(Grocery g : groceries){
            if(g.type.equalsIgnoreCase( "milk")){
                milkCount++;
                if (g.price == 1.23){
                    milkPrice123++;
                }
                else if(g.price == 3.23){
                    milkPrice323++;
                }
            }
            if(g.type.equalsIgnoreCase("bread")){
                breadcount++;
                if (g.price == 1.23){
                    breakprice123++;
                }
            }
            if(g.type.equalsIgnoreCase( "apple")){
                applecount++;
                if (g.price == .23){
                    applePrice23++;
                }
                else if (g.price == .25){
                    applePrice25++;
                }
            }
            if(g.type .equals("cookie")){
                cookiecount++;
                if(g.price == 2.25){
                    cookieprice++;
                }
            }
        }
        newHash.put("Milk", milkCount);
        newHash.put("Milk Price 1.23", milkPrice123 );
        newHash.put("Milk Price 3.23", milkPrice323);
        newHash.put("Bread", breadcount);
        newHash.put("Bread Price 1.23", breakprice123);
        newHash.put("Apple", applecount);
        newHash.put("Cookie", cookiecount);
        newHash.put("Apple Price .23", applePrice23);
        newHash.put("Apple Price .25", applePrice25);
        newHash.put("Cookie price", cookieprice);

        return newHash;

    }


    }







