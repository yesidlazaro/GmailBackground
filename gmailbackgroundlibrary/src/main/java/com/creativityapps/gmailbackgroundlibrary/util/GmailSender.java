package com.creativityapps.gmailbackgroundlibrary.util;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GmailSender extends javax.mail.Authenticator {
    private final String GMAIL_HOST = "smtp.gmail.com";
    private String user;
    private String password;
    private Session session;
    private Multipart _multipart;

    static {
        Security.addProvider(new JSSEProvider());
    }

    public GmailSender(String user, String password) {
        this.user = user;
        this.password = password;

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", GMAIL_HOST);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);
        _multipart = new MimeMultipart();
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    public synchronized void sendMail(String subject, String body, String sender, String mailTo,
                                      String mailCc, String mailBcc, String type) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);

        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), type));
        message.setDataHandler(handler);
        message.setText(body);
        if (_multipart.getCount() > 0) {
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            _multipart.addBodyPart(messageBodyPart);
            message.setContent(_multipart);
        }

        if (!TextUtils.isEmpty(mailTo)) {
            if (mailTo.indexOf(',') > 0) {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            } else {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            }
        }

        if (!TextUtils.isEmpty(mailCc)) {
            if (mailCc.indexOf(',') > 0) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailCc));
            } else {
                message.setRecipient(Message.RecipientType.CC, new InternetAddress(mailCc));
            }
        }

        if (!TextUtils.isEmpty(mailBcc)) {
            if (mailBcc.indexOf(',') > 0) {
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mailBcc));
            } else {
                message.setRecipient(Message.RecipientType.BCC, new InternetAddress(mailBcc));
            }
        }

        Transport.send(message);
    }

    public void addAttachment(String filename) throws Exception {
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        _multipart.addBodyPart(messageBodyPart);
    }

    public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }

        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContentType() {
            return type != null ? type : "application/octet-stream";
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }

        public String getName() {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }
}