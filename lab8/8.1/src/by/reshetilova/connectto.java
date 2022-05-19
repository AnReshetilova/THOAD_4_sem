package by.reshetilova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class connectto {
    public static void main(String[] args) {
        String urlName = "https://github.com/";
        getHTML(urlName);
    }
    public static void getHTML(String urlName)
    {
        URL url = null;
        String line;
        try {
            url = new URL(urlName);
        }  catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) {
            throw new RuntimeException();
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
