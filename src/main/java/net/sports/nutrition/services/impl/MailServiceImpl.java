package net.sports.nutrition.services.impl;

import net.sports.nutrition.services.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Service to work with the Mail, using MailSender.
 * .<p>
 * MailSender is annotated for automatic resource injection.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String body) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}