package upartsapiclient;

import java.util.ArrayList;


public class ClientMain {

    public static void main(String[] args) throws Exception {
        
        String host = "http://api.tiresync.com";
        String version = "v1";
        String db = "oe";
        String api_key = "1111-1111-1111-1111";
        String requestor = "localhost";
        
        UPartsAPIClient client = new UPartsAPIClient(host, version, db, api_key, requestor);
        
        ArrayList<String> years = client.fetch_years();
        for(int i = 0; i < years.size(); i++)
        {
            String year = years.get(i);
            System.out.println(year);
            ArrayList<String> makes = client.fetch_makes(year);
            for(int j = 0; j < makes.size(); j++)
            {
                String make = makes.get(j);
                System.out.println(make);
                ArrayList<String> models = client.fetch_models(year, make);
                for(int k = 0; k < models.size(); k++)
                {
                    String model = models.get(k);
                    System.out.println(model);
                    ArrayList<String> options = client.fetch_options(year, make, model);
                    for(int l = 0; l < options.size(); l++)
                    {
                        String option = options.get(l);
                        System.out.println(option);
                        Fitment fitment = client.fetch_specs(year, make, model, option);
                        System.out.println(fitment.toString());
                        System.exit(0);
                    }
                }
            }
        }    
    }
    
}
