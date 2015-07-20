/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalyzer;

import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author Nikolin
 */
public class HTTPRequests {
    
    public String url;
    public String keyword;
    public String decode;
    public String fromStringFormat;
    public String toSringFormat;
    
    public HTTPRequests(LocalDateTime from, LocalDateTime to, String keyword, String decode, String url){
        this.fromStringFormat = from.toString().replace("T", "%20") + ":00";
        this.toSringFormat = to.toString().replace("T", "%20") + ":00";
        this.keyword = keyword;
        this.decode = decode;
        this.url = url.replace("[[from]]", this.fromStringFormat).replace("[[to]]", this.toSringFormat).replace("[[key]]", this.keyword).replace("[[decode]]", this.decode); 
        System.out.println(this.url);
    }
    
    public String getHTTPResponce_readURL(){
        try{
            URL jsonData = new URL(this.url);
            URLConnection connection = jsonData.openConnection();  
            connection.setDoOutput(true);  

            String response;
            try (Scanner scanner = new Scanner(jsonData.openStream())) {
                response = scanner.useDelimiter("\\Z").next();
                response = "{\"tweetsFeedback\":[" + response + "]}";
                response = response.replace("}{", "},{");
                //System.out.println(response);
            }
            
            //JOptionPane.showMessageDialog(null, "HTTP Request Successful", "HTTP Request", JOptionPane.INFORMATION_MESSAGE);
            
            return response;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
       
}
