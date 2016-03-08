package net.sports.nutrition.services;

import org.springframework.mail.MailException;

/**
 * Service to work with the Mail.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IMailService {

    /**
     * Sends message
     *
     * @param to      - email recipient
     * @param subject - subject of email
     * @param body    - body of email
     * @throws MailException
     */
    void sendMail(String to, String subject, String body) throws MailException;
}
