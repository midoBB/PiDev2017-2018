package tn.esprit.bonplan.API;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.esprit.bonplan.entities.Promotion;
import tn.esprit.bonplan.entities.User;

public class EnvoyerEmail {

    private String username = "nbaolnp@gmail.com";
    private String password = "147852369A";

    public void EnvoyerMail (List<User> ls, Promotion P) {
        /*

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
         for (User u : ls) {
        try {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nbaolnp@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(u.getMail()));
            message.setSubject("Promotion");
            message.setText("Bonjour, BonPlan vous informe d'une promotion de "+P.getCota()+"% qui aura lieu dans "+P.getRefEtab()+" a partire du "+P.getDateDebut());
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }*/
    }

} 
