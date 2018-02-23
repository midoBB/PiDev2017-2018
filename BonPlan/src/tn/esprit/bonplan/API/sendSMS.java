package tn.esprit.bonplan.API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import tn.esprit.bonplan.entities.Evenement;
import tn.esprit.bonplan.entities.User;

public class sendSMS {

    public static void sendSms(List<User> ls, Evenement e) {
        /*for (User u : ls) {
            try {
                // Construct data
                String apiKey = "apikey=" + "ezLffKXjT0M-fh2qrJd0s9QQIjXGUJerqjq2LaDMax";
                String message = "&message=" + " Bon Plan vous informe d'un nouveau Evnnement :  "+e.getNom()+" qui aura lieu : "+e.getDateDebut();
                String sender = "&sender=" + "BonPlan";
                String numbers = "&numbers=" +u.getNum();

                // Send data
                HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
                String data = apiKey + numbers + message + sender;
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                conn.getOutputStream().write(data.getBytes("UTF-8"));
                final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                final StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuffer.append(line);
                }
                rd.close();

            } catch (Exception erreur) {
                System.out.println("Error SMS " + erreur);
               
            }
        }
        */
    }
}
