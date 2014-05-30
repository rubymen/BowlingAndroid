package com.rubymen.bowlingandroid.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * REST connector which include a list of k-v headers/params
 */
public class RestProvider {

    private ArrayList<NameValuePair> params;
    private ArrayList<NameValuePair> headers;

    private int responseCode;
    private String errorMessage;
    private String model;
    private String response;
    private String url;

    public int getResponseCode() {
        return responseCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getResponse() {
        return response;
    }

    /**
     * Constructor of a provider with url
     * @param url Url of data
     */
    public RestProvider(String url) {
        this.url = url;
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
    }

    /**
     * Constructor of a provider with url and object which serves of key for params
     * @param url Url of data
     * @param model Key for all the params
     */
    public RestProvider(String url, String model) {
        this.url = url;
        this.model = model;
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
    }

    public void AddParam(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }

    public void AddHeader(String name, String value) {
        headers.add(new BasicNameValuePair(name, value));
    }

    public void Execute(RequestMethod method) throws Exception {
        switch (method) {
            case GET: {
                String getParams = "";

                if (!params.isEmpty()) {
                    getParams += "?";

                    for (NameValuePair p : params) {
                        String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");

                        if (getParams.length() > 1) {
                            getParams += "&" + paramString;
                        } else {
                            getParams += paramString;
                        }
                    }
                }

                HttpGet request = new HttpGet(url + getParams);

                for (NameValuePair h : headers) {
                    request.addHeader(h.getName(), h.getValue());
                }

                executeRequest(request, url);
                break;
            }
            case POST: {
                HttpPost request = new HttpPost(url);

                for (NameValuePair h : headers) {
                    request.addHeader(h.getName(), h.getValue());
                }

                if (!params.isEmpty()) {
                    Gson gson = new GsonBuilder().create();
                    Map<String, String> paramsKeyValue = new HashMap<String, String>();

                    for (NameValuePair p : params) {
                        paramsKeyValue.put(p.getName(), p.getValue());
                    }

                    String json = gson.toJson(paramsKeyValue);
                    StringEntity se = new StringEntity(json.toString());

                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    request.setEntity(se);
                }

                executeRequest(request, url);
                break;
            }
        }
    }

    private void executeRequest(HttpUriRequest request, String url) {
        HttpClient client = new DefaultHttpClient();

        HttpResponse httpResponse;

        try {
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            errorMessage = httpResponse.getStatusLine().getReasonPhrase();

            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                InputStream stream = entity.getContent();
                response = convertStreamToString(stream);

                stream.close();
            }

        } catch (ClientProtocolException e)  {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        } catch (IOException e) {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        }
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

}
