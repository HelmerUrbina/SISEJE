package Utiles;

import BusinessServices.Beans.BeanUsuario;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {

    static final String FROMNAME = "Helmer Urbina";
    static final String SMTP_USERNAME = "helmerurbina@gmail.com";
    static final String SMTP_PASSWORD = "17101985";
    static final String CONFIGSET = "ConfigSet";
    static final String HOST = "smtp.gmail.com";
    static final int PORT = 587;
    static final String SUBJECT = "Recuperar Password SISEJE";
    private String TO = "";
    static String BODY = "";

    public JavaMail(BeanUsuario objBeanUsuario) {
        TO = objBeanUsuario.getCorreo();
        BODY = String.join(
                System.getProperty("line.separator"),
                "<h1>Recuperar Password SISEJE</h1>",
                "<p>Apellidos y Nombres : " + objBeanUsuario.getPaterno() + " " + objBeanUsuario.getMaterno() + ", " + objBeanUsuario.getNombres(),
                "<p>Usuario : " + objBeanUsuario.getUsuario(),
                "<p>Password : " + objBeanUsuario.getPassword(),
                "<p>Fecha : " + Utiles.fechaServidor(),
                "<p>Sistema Enviado bajo la Plataforma Java."
        );
    }

    public String SendMail() {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(SMTP_USERNAME, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            msg.setSubject(SUBJECT);
            msg.setContent(BODY, "text/html");
            Transport transport = session.getTransport();
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            return "VCorreo Enviado con Exito";
        } catch (MessagingException | UnsupportedEncodingException e) {
            return "F" + e.getMessage();
        }
    }
}
