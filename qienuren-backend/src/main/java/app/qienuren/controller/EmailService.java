package app.qienuren.controller;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
