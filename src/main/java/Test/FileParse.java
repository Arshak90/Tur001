package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ArtStyle on 27.04.2017.
 */
public class FileParse {
    public static void main(String[] args) {
        System.out.println("Start ....");
        File file = new File("/usr/local/www/apache24/data/.htpasswd");
        Scanner input = null;
        System.out.println("Start");
        try {
            input = new Scanner(file);
            System.out.println("File input");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("While start");
        while(input.hasNext()) {
            String nextToken = input.next();
            System.out.println(nextToken);
        }
        System.out.println("While end");
        input.close();
    }
}
