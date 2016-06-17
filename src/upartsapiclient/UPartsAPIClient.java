package upartsapiclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.util.Base64;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONArray;

public class UPartsAPIClient {

    private String host;
    private String version;
    private String db;
    private String api_key;
    private String requestor;

    public UPartsAPIClient(String host, String version, String db,
            String api_key, String requestor) {
        this.host = host;
        this.version = version;
        this.db = db;
        this.api_key = api_key;
        
        this.requestor = this.encode(requestor);
    }

    public String getHost() {
        return new String(this.host);
    }

    public String getVersion() {
        return new String(this.version);
    }

    public String getDB() {
        return new String(this.db);
    }

    public String getAPIKey() {
        return new String(this.api_key);
    }

    public String getRequestor() {
        return new String(this.requestor);
    }

    public ArrayList<String> fetch_years() throws Exception {

        String url = this.host + "/"
                + this.version + "/"
                + this.db + "/"
                + "years/"
                + this.api_key + "/"
                + this.requestor;
        //System.out.println(url);

        String response = this.send_request(url);

        JSONArray years = new JSONObject(response)
                .getJSONArray("items");
        
        return this.get_list(years);

    }

    public ArrayList<String> fetch_makes(String year) throws Exception {
        String url = this.host + "/"
                + this.version + "/"
                + this.db + "/"
                + "makes/"
                + this.api_key + "/"
                + this.requestor + "/"
                + this.encode(year);
        //System.out.println(url);
        
        JSONArray makes = new JSONObject(this.send_request(url))
                .getJSONArray("items");
        
        return this.get_list(makes);
    }
    
    public ArrayList<String> fetch_models(String year, String make) throws Exception
    {
        String url = this.host + "/"
                + this.version + "/"
                + this.db + "/"
                + "models/"
                + this.api_key + "/"
                + this.requestor + "/"
                + this.encode(year) + "/"
                + this.encode(make);
        //System.out.println(url);
        
        
        
        JSONArray models = new JSONObject(this.send_request(url))
                .getJSONArray("items");
        
        return this.get_list(models);
    }
    
    public ArrayList<String> fetch_options(String year, String make, String model) throws Exception
    {
        String url = this.host + "/"
                + this.version + "/"
                + this.db + "/"
                + "options/"
                + this.api_key + "/"
                + this.requestor + "/"
                + this.encode(year) + "/"
                + this.encode(make) + "/"
                + this.encode(model);
        //System.out.println(url);
        
        
        
        JSONArray options = new JSONObject(this.send_request(url))
                .getJSONArray("items");
        
        return this.get_list(options);
    }
    
    public Fitment fetch_specs(String year, String make, String model, String option) throws Exception
    {
        String url = this.host + "/"
                + this.version + "/"
                + this.db + "/"
                + "vehicle_fitments/"
                + this.api_key + "/"
                + this.requestor + "/"
                + this.encode(year) + "/"
                + this.encode(make) + "/"
                + this.encode(model) + "/"
                + this.encode(option);
        //System.out.println(url);
                
        JSONArray options = new JSONObject(this.send_request(url))
                .getJSONArray("items");

        Iterator it = options.iterator();
        JSONObject result = options.getJSONObject(0);
        String section_width = result.getString("SECTIONWIDTH");
        String aspect_ratio = result.getString("ASPECTRATIO");
        String rim = result.getString("RIM");
        
        return new Fitment(section_width, aspect_ratio, rim);
    }

    private String send_request(String url) throws Exception {
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();

        //System.out.println("Response Code: " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
    
    private String encode(String toEncode)
    {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = toEncode.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(encodedBytes);   
    }
    
    private ArrayList<String> get_list(JSONArray arr)
    {
        ArrayList<String> ret = new ArrayList<>();
        
        Iterator it = arr.iterator();
        while(it.hasNext())
        {
            ret.add(it.next().toString());
        }
        return ret;
    }

}
