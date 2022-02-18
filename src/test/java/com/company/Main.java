package com.company;

/*import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;*/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Main {
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        HashMap<Integer, URL> StatusAndLink = new HashMap<>();
        Document doc = Jsoup.connect("http://www.google.com/").get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select("a");
        for (Element headline : newsHeadlines) {
           /*System.out.printf("%s\n\t%s\n",
                    headline.attr("title"), headline.absUrl("href"));*/
            URL url = new URL(headline.absUrl("href"));
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            int statusCode = http.getResponseCode();
            //System.out.println(statusCode);
            StatusAndLink.put(statusCode, url);
            Link link = new Link(url, statusCode);
            //System.out.println(StatusAndLink);
            String json = GSON.toJson(link);
            System.out.println(json);
        }




    }
   static class Link{
    private URL url;
    private Integer StatusCode;

       public Link(URL url, Integer statusCode) {
           this.url = url;
           StatusCode = statusCode;
       }
   }
}
/*  String url = "http://www.google.com/";
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //
        System.out.println(response.toString());
        ***********************
         URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            url = new URL("http://www.stackoverflow.com");
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                if(line.contains("href="))
                    System.out.println(line.trim());
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                //exception
            }
        }




           String html = Jsoup.connect("http://www.google.com/").get().html();
           Document doc = Jsoup.connect("http://www.google.com/").get();
           System.out.println(doc.title());
            Elements newsHeadlines = doc.select(":any-link");
            for (Element headline : newsHeadlines) {
              System.out.printf("%s\n\t%s\n",
                headline.attr("title"), headline.absUrl("href"));
            }

        Scanner in = new Scanner(html);

        Elements links = null;
        String line = in.nextLine();
        while (in.hasNext()) {
            if (line.contains("sometext")) {

                links += line.substring(line.indexOf("http").line.indexOf("</a>") + "\n");

            }
            String output = "";
// Get the webpage and parse it.
            String url = "http://www.google.com/";
            Document doc = Jsoup.connect(url).get();
// Get the anchors with href attribute.
// Or, you can use doc.select("a") to get all the anchors.
            links = doc.select("a[href]");
// Iterate over all the links and process them.
            for (Element link : links) {
                output += link.attr("abs:href");
            }


        }
    }
        */

 /*HttpURLConnection http = (HttpURLConnection) .openConnection();
            int statusCode = http.getResponseCode();
            System.out.println(statusCode);*/

