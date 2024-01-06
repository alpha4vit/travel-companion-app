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

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;



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
    public void sendConfirmationCode(User user){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = null;
                try {
                    MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
                    helper.setFrom(mailProperties.getUsername());
                    helper.setTo(user.getEmail());
                    helper.setSubject(String.format("Уважаемый, %s, ваш код подтверждения почты:", user.getUsername()));
                    helper.setText(user.getConfirmationCode());
                    mailSender.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
}
