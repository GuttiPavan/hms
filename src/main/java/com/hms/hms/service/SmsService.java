package com.hms.hms.service;

import com.hms.hms.entity.OtpDetails;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

@Service
public class SmsService {
    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    @Value("${twilio.whatsapp.from}")
    private String twilioWhatsAppNumber;

   // @Value("${sendgrid.api.key}")
    //private String sendGridApiKey;

    public String sendSms(String to, String body) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(twilioPhoneNumber),
                    body
            ).create();

            return "Message sent successfully with SID: " + message.getSid();
        } catch (Exception e) {
            return "Failed to send message: " + e.getMessage();
        }

    }


    // Send WhatsApp Message
    public String sendWhatsAppMessage(String to, String messageBody) {
        Message message = Message.creator(
                new PhoneNumber(to), // Recipient's WhatsApp number
                new PhoneNumber(twilioWhatsAppNumber), // Twilio's WhatsApp number
                messageBody
        ).create();
        return "WhatsApp message sent successfully with SID: " + message.getSid();
    }






   /*public String sendEmail(String toEmail, String subject, String content) {
        Email from = new Email("mdsaqheeb44@gmail.com");  // Replace with your verified SendGrid email
        Email to = new Email(toEmail);
        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, to, emailContent);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            return response.getBody();
        } catch (IOException ex) {
            return "Error sending email: " + ex.getMessage();
        }


    }*/


}
