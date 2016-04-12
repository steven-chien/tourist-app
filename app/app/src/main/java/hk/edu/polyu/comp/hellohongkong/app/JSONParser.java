package hk.edu.polyu.comp.hellohongkong.app;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by zhoumoyuan on 15/11/22.
 */
public class JSONParser {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public JSONObject makeHttpRequest(String urlString, String method, List<Pair> params) throws IOException {
        try {
            // Making HTTP request
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[] { new MyTrustManager() }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());

            // check for request method
            if(Objects.equals(method, "POST")){
                // request method is POST
                // http client
                URL url= new URL(urlString);
                HttpURLConnection httpClient = (HttpURLConnection) url.openConnection();
                httpClient.setRequestMethod("POST");
                httpClient.setDoOutput(true);
                httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                httpClient.setRequestProperty("Connection", "Keep-Alive");

                OutputStream os = httpClient.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                System.out.println("MZ params: " + getQuery(params));
                writer.write(getQuery(params));
                writer.flush();
                writer.close();
                os.close();
                httpClient.connect();

                if (httpClient.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    is = httpClient.getInputStream();
                }
                else{
                    System.out.println("code: " + httpClient.getResponseCode());
                }

            }else if(Objects.equals(method, "GET")){
                // request method is GET
                // http client
                System.out.println("MZ params: " + getQuery(params));
                URL url= new URL(urlString + "?" + getQuery(params));
                HttpURLConnection httpClient = (HttpURLConnection) url.openConnection();
                httpClient.setRequestMethod("GET");
                httpClient.setRequestProperty("Connection", "Keep-Alive");
                httpClient.connect();

                if (httpClient.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    is = httpClient.getInputStream();
                } else{
                    System.out.println("code: " + httpClient.getResponseCode());
                }
            }
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            System.out.println("MZ json string is: " + json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }

    public JSONObject makeHttpRequest(String urlString, String method) throws IOException {
        List<Pair> params = new ArrayList<Pair>();
        return makeHttpRequest(urlString, method, params);
    }

    private String getQuery(List<Pair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Pair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.first.toString(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.second.toString(), "UTF-8"));
        }

        return result.toString();
    }

    private class MyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            // TODO Auto-generated method stub
            return true;
        }
    }

    private class MyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
                    // TODO Auto-generated method stub
            return null;
        }

    }
}
