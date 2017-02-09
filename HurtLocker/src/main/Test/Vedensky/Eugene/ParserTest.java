package Vedensky.Eugene;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by eugenevendensky on 2/9/17.
 */
public class ParserTest {
    Parser parser;

    @Before
    public void setUp(){ parser = new Parser();}

    @Test
    public void splitStringsTest(){
        String actual = Arrays.toString(parser.splitStrings(parser.rawData));
        int expected =  parser.splitStrings(parser.rawData).length;

        assertEquals(expected, actual);
    }
    @Test
    public void matchFoodItemsTest() throws CustomException {
        String testString = "naME:c0okies;price:1.23;type:Food;expiration:1/02/2016";
        String actual = parser.getFoodMatches(testString);
        String expected = "cookie";
        assertEquals(expected, actual);



    }

    @Test
    public void matchPriceTest() throws CustomException {
        String testString = "naME:c0okies;price:1.23;type:Food;expiration:1/02/2016";

        double actual = parser.matchPrice(testString);
        double expected = 1.23;
        assertEquals(expected, actual);

    }
    @Test
    public void parseEntryToGroceryObjectTest() throws  CustomException {
        String[] testStringArray = parser.splitStrings(parser.rawData);
        ArrayList<Grocery> testList = parser.parseEntryToGrocery(testStringArray);
        int actual = testList.size();
        int expected = 0;
        assertEquals(expected, actual);
    }
    @Test
    public void testAmountOfErrors(){
        String[] testStringArray = parser.splitStrings(parser.rawData);
        ArrayList<Grocery> testList = parser.parseEntryToGrocery(testStringArray);
        int actual = CustomException.errorCount;
        int expected = 0;
        assertEquals(expected, actual);

    }
}
