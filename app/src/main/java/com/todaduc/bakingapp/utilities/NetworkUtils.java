package com.todaduc.bakingapp.utilities;

import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


/**
 * This utility class will be used to interact with the recipe servers.
 */
public class NetworkUtils {


    public static URL buildUrl(String movieSearchQuery, String baseUrl, String apiKey, String apiValue) {
        Uri builtUri = Uri.parse(baseUrl).buildUpon().appendPath(movieSearchQuery)
                .appendQueryParameter(apiKey, apiValue)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildUrlId(String movieSearchQuery,String id, String baseUrl, String apiKey, String apiValue) {
        Uri builtUri = Uri.parse(baseUrl).buildUpon().appendPath(id).appendPath(movieSearchQuery)
                .appendQueryParameter(apiKey, apiValue)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * builds a simple URL out of a string object
     * @param urlRequest the given string
     * @return the URL object
     */
    public static URL buildSimpleUrl (String urlRequest){
        Uri builtUri = Uri.parse(urlRequest);
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
