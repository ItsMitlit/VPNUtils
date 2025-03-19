package it.mitl.vpnutils.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class APIRequests {

    public static boolean checkVPN(String userIP) {
        try {
            String url = "https://antivpn.mitl.it/api/v1/checkip?ip=" + userIP;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getBoolean("isVPN");
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return false;
    }

    public static int getAPIVersionInfo() {
        try {
            String url = "https://antivpn.mitl.it/api/v1/versionstatus";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                if (jsonResponse.getString("support").equals("active")) {
                    return 1;
                } else if (jsonResponse.getString("support").equals("ending")) {
                    return 2;
                } else if (jsonResponse.getString("support").equals("ended")) {
                    return 3;
                } else {
                    return 0;
                }
            } return 0;

        } catch (Exception error) {
            error.printStackTrace();
        }
    return 0;
    }
}
