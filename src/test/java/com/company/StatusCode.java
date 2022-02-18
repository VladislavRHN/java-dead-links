package com.company;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class StatusCode{
    public static void main(String args[]) throws IOException {
        URL url = new URL("https://www.google.com/history/optout?hl=uk&fg=1");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        int statusCode = http.getResponseCode();
        System.out.println(statusCode);
        }
    }

