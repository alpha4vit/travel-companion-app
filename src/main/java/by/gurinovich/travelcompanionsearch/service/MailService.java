package by.gurinovich.travelcompanionsearch.service;

import by.gurinovich.travelcompanionsearch.model.Post;
import by.gurinovich.travelcompanionsearch.model.PostResponse;
import by.gurinovich.travelcompanionsearch.model.User;
import by.gurinovich.travelcompanionsearch.props.MailProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import freemarker.template.Configuration;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;
    private final Configuration configuration;


    public void sendResponseMessage(Post post, PostResponse postResponse){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = null;
                try {
                    messageHelper = new MimeMessageHelper(message, false, "UTF-8");
                    messageHelper.setFrom(mailProperties.getUsername());
                    messageHelper.setTo(post.getUser().getEmail());
                    messageHelper.setSubject(String.format("У вас новый отклик на объявление \" %s \" от пользователя %s", post.getTitle(), postResponse.getUser().getUsername()));
                    messageHelper.setText("Comment: "+postResponse.getComment());
                    mailSender.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    @SneakyThrows
    public void sendRegistrationEmailMessage(User user){
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                MimeMessage message = mailSender.createMimeMessage();
                try {
                    MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
                    helper.setFrom(mailProperties.getUsername());
                    helper.setTo(user.getEmail());
                    helper.setSubject("Thank you for registration, dear" + user.getUsername());
                    String emailContent = getEmailMessageForRegistration(user);
                    helper.setText(emailContent, true);
                    mailSender.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        th.start();
    }

    @SneakyThrows
    public void sendPasswordResetMessage(User user){
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                MimeMessage message = mailSender.createMimeMessage();
                try {
                    System.out.println("start");
                    MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
                    helper.setFrom(mailProperties.getUsername());
                    helper.setTo(user.getEmail());
                    helper.setSubject("Reset your password");
                    String emailContent = getEmailMessageForPasswordReset(user);
                    helper.setText(emailContent, true);
                    mailSender.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        th2.start();
    }

    @SneakyThrows
    public String getEmailMessageForRegistration(User user){
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("username", user.getUsername());
        model.put("code", user.getConfirmationCode());
        configuration.getTemplate("mail/registration.flth")
                .process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    @SneakyThrows
    public String getEmailMessageForPasswordReset(User user){
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("username", user.getUsername());
        model.put("id", user.getId());
        model.put("link", mailProperties.getResetLink());
        configuration.getTemplate("mail/password_reset.flth")
                .process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
