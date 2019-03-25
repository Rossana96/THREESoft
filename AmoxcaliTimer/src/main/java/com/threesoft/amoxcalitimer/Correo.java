/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threesoft.amoxcalitimer;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author damianri
 */
public class Correo {

    private final static String EMAIL = "gestordeespaciosculturalesfacu@gmail.com"; 
    private final static String PASSWORD = "gestordec123";   
    private final static String CORREO_DE_ACTIVACION = "Buen día, %s<br>"
            + "Tu registro ha sido verificado, ahora cuenta con acceso al sistema<br>"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>"
            + ", ahora puede iniciar sesión. <br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
    private final static String CORREO_DE_REGISTRO = "Buen día, %s<br>"
            + "Tu registro al sistema <br>"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>"
            + " está siendo revisado, por favor espera el correo de aceptación. <br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
        private final static String CORREO_DE_ADMIN = "Buen día, %s<br>"
            + "Se te ha dado de alta como <b>administrador</b> en el sistema<br>"
            + "<a href=\"%s\"><b>Gestor de Espacios Culturades</b></a>"
            + ", ahora puede iniciar sesión con los siguientes datos: <br>"
            + "Correo: %s<br> Contraseña: %s<br>"
            + "Se recomienda ingresar al sistema y hacer el cambio de contraseña por una personal.<br><br>"
            + "Administración de Espacios Culturales<br> Facultad de Ciencias";
    
    private final static Properties MAIL_SERVER_PROPERTIES;
    private static Session getMailSession;
    private static MimeMessage generateMailMessage;

    static {
        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        MAIL_SERVER_PROPERTIES = System.getProperties();
        MAIL_SERVER_PROPERTIES.put("mail.smtp.port", "587");
        MAIL_SERVER_PROPERTIES.put("mail.smtp.auth", "true");
        MAIL_SERVER_PROPERTIES.put("mail.smtp.starttls.enable", "true");
        MAIL_SERVER_PROPERTIES.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        System.out.println("Mail Server Properties have been setup successfully..");
    }

     public static void correoDeRegistro(String mailDestinatario, String nombreCompletoUsuario) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestinatario));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Registro.");
        generateMailMessage.setContent(String.format(CORREO_DE_REGISTRO, nombreCompletoUsuario, "http://localhost:8084/AmoxcaliTimer/"), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }
     
     public static void correoDeActivacion(String mailDestinatario, String nombreCompletoUsuario) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestinatario));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Activación.");
        generateMailMessage.setContent(String.format(CORREO_DE_ACTIVACION, nombreCompletoUsuario, "http://localhost:8084/AmoxcaliTimer/"), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }
     
     public static void correoDeActivacionAdmin(String mailDestinatario, String nombreCompletoUsuario,String passwordTemporal) throws AddressException, MessagingException {
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(MAIL_SERVER_PROPERTIES, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestinatario));
        //generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
        generateMailMessage.setSubject("Gestor de Espacios Culturales. Activación.");
        generateMailMessage.setContent(String.format(CORREO_DE_ADMIN, nombreCompletoUsuario, "http://localhost:8084/AmoxcaliTimer/", mailDestinatario, passwordTemporal), "text/html");
        System.out.println("Mail Session has been created successfully..");
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        try (Transport transport = getMailSession.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        }
    }
}