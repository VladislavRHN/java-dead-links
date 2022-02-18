package com.company;

import com.sun.istack.internal.NotNull;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.xml.sax.SAXException;
import sun.applet.Main;
//import sun.applet.Main
//import sun.applet.Main;

import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class FindDeadLinksTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    private final String url = "https://mail.google.com/mail/&ogbl";
    private String expected = "";


    @Before
    @NotNull
    public void setExpectedOutputFromJsonFile() {

        try {
            InputStreamReader inputStream = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader()
                    .getResourceAsStream("expected_result.json")));
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();


            for (String line; (line = bufferedReader.readLine()) != null; ) {
                stringBuilder.append(line).append("\n");
            }
            expected = stringBuilder.toString();
        }
        //
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //

    // System.setErr переопределяет поток ошибок
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);

    }
     /*if(url == null || url.isEmpty()){

        System.out.println("URL is either not configured for anchor tag or it is empty");
        continue;

    }

            if(!url.startsWith(homePage)){
        System.out.println("URL belongs to another domain, skipping it.");
        continue;*/

    @Test
    public void findLinks() throws IOException, SAXException {
        Main.main(new String[]{url});
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void findLinksNegative() throws IOException, SAXException {
        Main.main(new String[]{url});//
        assertNotEquals(expected+1, outContent.toString());
    }
}




