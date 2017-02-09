package Vedensky.Eugene;

/**
 * Created by eugenevendensky on 2/9/17.
 */
public class CustomException extends Exception  {

    static int errorCount = 0;

    public CustomException(){
        errorCount++;
    }
}
