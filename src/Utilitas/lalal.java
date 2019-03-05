/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitas;

/**
 *
 * @author RO'I FAHREZA
 */
    import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lalal
{
    private static final String daftarstopword = "baik|bisa|ya|tidak";
    static String stop = "\\b(" + daftarstopword+ ")\\b\\s*";
    static final String INPUT = "sekali baik tetap baik";
    static Pattern pattern;
    static Matcher matcher;

    public static void main( String args[] ){
       Pattern stopword = Pattern.compile(stop, Pattern.CASE_INSENSITIVE);
       matcher = stopword.matcher(INPUT);
       System.out.println("lookingAt(): "+matcher.lookingAt());
        System.out.println(""+matcher.matches());
    }
}

