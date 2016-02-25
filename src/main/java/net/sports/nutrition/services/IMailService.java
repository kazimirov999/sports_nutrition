package net.sports.nutrition.services;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 09.02.2016 19:07
 */
public interface IMailService {

    void sendMail(String to, String subject, String body);
}
