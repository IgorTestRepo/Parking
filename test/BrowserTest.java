/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author jesus
 */
public class BrowserTest {

    private final String[] USER_AGENT = new String[]{"Mozilla/5.0 (X11; Linux i686; rv:44.0) Gecko/20100101 Firefox/44.0",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)", "Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko"};
    private final String HOST = "localhost";
    private final String ACCEPT = "text/html,application/xhtml+xml,application/json,application/xml;q=0.9,*/*;q=0.8";
    private final String ACCEPTLANG = "en-US,en;q=0.5";
    private final String ACCEPTENC = "gzip, deflate";
    private static final Logger LOG = Logger.getLogger(BrowserTest.class.getName());

    public String sendPUT(String url, String json) throws IOException {
        LOG.log(Level.INFO, "send PUT request to URL : {0} ", url);

        URL obj = new URL(url);
        HttpURLConnection connectHttp = (HttpURLConnection) obj.openConnection();

        connectHttp.setRequestMethod("PUT");
        connectHttp.setUseCaches(false);
        connectHttp.setDoOutput(true);
        connectHttp.setRequestProperty("Host", HOST);
        connectHttp.setRequestProperty("User-Agent", USER_AGENT[0]);
//        connectHttp.setRequestProperty("Accept", ACCEPT);
        connectHttp.setRequestProperty("Accept-Language", ACCEPTLANG);
        connectHttp.setRequestProperty("Accept-Encoding", ACCEPTENC);
        connectHttp.setRequestProperty("Connection", "keep-alive");
        connectHttp.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = connectHttp.getOutputStream()) {
            os.write(json.getBytes());
            os.flush();
        }

        String contentEncoding = connectHttp.getContentEncoding();
        int responseCode = connectHttp.getResponseCode();
        LOG.log(Level.INFO, "\nSending GET request to URL : {0} \n Response Code : {1}",
                new Object[]{url, responseCode});
        StringBuilder response = null;
        InputStreamReader inReader;

//        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
        if ("gzip".equals(contentEncoding)) {
            inReader = new InputStreamReader(new GZIPInputStream(responseCode
                    == HttpURLConnection.HTTP_OK ? connectHttp.getInputStream() : connectHttp.getErrorStream()));
        } else {
            inReader = new InputStreamReader(responseCode
                    == HttpURLConnection.HTTP_OK ? connectHttp.getInputStream() : connectHttp.getErrorStream());
        }

        try (BufferedReader in = new BufferedReader(inReader)) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            inReader.close();
        }
//        } else {

//            LOG.log(Level.WARNING, "Response code={0}", responseCode);
//            throw new BrowserResponseException("sendGETPageContent() responseCode=" + responseCode);
//        }
        return response.toString();
    }

    public String sendPOST(String url, String json) throws IOException {
        LOG.log(Level.INFO, "send PUT request to URL : {0} ", url);

        URL obj = new URL(url);
        HttpURLConnection connectHttp = (HttpURLConnection) obj.openConnection();

        connectHttp.setRequestMethod("POST");
        connectHttp.setUseCaches(false);
        connectHttp.setDoOutput(true);
        connectHttp.setRequestProperty("Host", HOST);
        connectHttp.setRequestProperty("User-Agent", USER_AGENT[0]);
//        connectHttp.setRequestProperty("Accept", ACCEPT);
        connectHttp.setRequestProperty("Accept-Language", ACCEPTLANG);
        connectHttp.setRequestProperty("Accept-Encoding", ACCEPTENC);
        connectHttp.setRequestProperty("Connection", "keep-alive");
        connectHttp.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = connectHttp.getOutputStream()) {
            os.write(json.getBytes());
            os.flush();
        }

        String contentEncoding = connectHttp.getContentEncoding();
        int responseCode = connectHttp.getResponseCode();
        LOG.log(Level.INFO, "\nSending GET request to URL : {0} \n Response Code : {1}",
                new Object[]{url, responseCode});
        StringBuilder response = null;
        InputStreamReader inReader;

//        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
        if ("gzip".equals(contentEncoding)) {
            inReader = new InputStreamReader(new GZIPInputStream(responseCode
                    == HttpURLConnection.HTTP_OK ? connectHttp.getInputStream() : connectHttp.getErrorStream()));
        } else {
            inReader = new InputStreamReader(responseCode
                    == HttpURLConnection.HTTP_OK ? connectHttp.getInputStream() : connectHttp.getErrorStream());
        }

        try (BufferedReader in = new BufferedReader(inReader)) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            inReader.close();
        }
//        } else {

//            LOG.log(Level.WARNING, "Response code={0}", responseCode);
//            throw new BrowserResponseException("sendGETPageContent() responseCode=" + responseCode);
//        }
        return response.toString();
    }

    public String sendGET(String url) throws IOException {

        LOG.log(Level.INFO, "send GET request to URL : {0}", url);

        URL obj = new URL(url);
        HttpURLConnection connectHttp = (HttpURLConnection) obj.openConnection();

        connectHttp.setRequestMethod("GET");
        connectHttp.setUseCaches(false);

        connectHttp.setRequestProperty("Host", HOST);
        connectHttp.setRequestProperty("User-Agent", USER_AGENT[0]);
//        connectHttp.setRequestProperty("Accept", ACCEPT);
        connectHttp.setRequestProperty("Accept-Language", ACCEPTLANG);
        connectHttp.setRequestProperty("Accept-Encoding", ACCEPTENC);
        connectHttp.setRequestProperty("Connection", "keep-alive");
        connectHttp.setRequestProperty("Content-Type", "application/json");

        String contentEncoding = connectHttp.getContentEncoding();
        int responseCode = connectHttp.getResponseCode();
        LOG.log(Level.INFO, "\nSending GET request to URL : {0} \n Response Code : {1}",
                new Object[]{url, responseCode});
        StringBuilder response = null;
        InputStreamReader inReader;

//        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
        if ("gzip".equals(contentEncoding)) {
            inReader = new InputStreamReader(new GZIPInputStream(responseCode
                    == HttpURLConnection.HTTP_OK ? connectHttp.getInputStream() : connectHttp.getErrorStream()));
        } else {
            inReader = new InputStreamReader(responseCode
                    == HttpURLConnection.HTTP_OK ? connectHttp.getInputStream() : connectHttp.getErrorStream());
        }

        try (BufferedReader in = new BufferedReader(inReader)) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            inReader.close();
        }
//        } else {

//            LOG.log(Level.WARNING, "Response code={0}", responseCode);
//            throw new BrowserResponseException("sendGETPageContent() responseCode=" + responseCode);
//        }
        return response.toString();
    }

    public static void main(String[] args) throws IOException {

        BrowserTest bt = new BrowserTest();

        String jsonAdd = "{\"number\":\"A104УР177\", \"model\":\"Mazda\", \"startTime\": \"2017-09-28 12:00:00\"}";

        String jsonRemove = "{\"number\":\"A104УР177\", \"payment\":500, \"endTime\": \"2017-09-28 17:00:00\"}";
        Date date = new Date(System.currentTimeMillis());
//        System.out.println(date.);
//        try {
            System.out.println("ADD=" + bt.sendPUT("http://localhost:8080/Parking/parking/park/in", jsonAdd));
//            System.out.println("REMOVE=" + bt.sendPOST("http://localhost:8080/Parking/parking/park/out", jsonRemove));
            System.out.println("Parked=" + bt.sendGET("http://localhost:8080/Parking/parking/result/parked/"));
//            System.out.println("FREE=" + bt.sendGET("http://localhost:8080/Parking/parking/result/free/"));
//            System.out.println("Money=" + bt.sendGET("http://localhost:8080/Parking/parking/result/money/2017-09-29"));
//
//            System.out.println("HAVING=" + bt.sendGET("http://localhost:8080/Parking/parking/result/exist/" + URLEncoder.encode("A116УР177", "UTF-8")));
//        } catch (IOException ex) {
//
//            Logger.getLogger(BrowserTest.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
