package rock.dong;

import org.apache.logging.log4j.message.SimpleMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootTaskApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage simpleMailMessage
                = new SimpleMailMessage();

        simpleMailMessage.setSubject("test");
        simpleMailMessage.setText("今晚7:30开会");
        simpleMailMessage.setFrom("1982-615@163.com");
        simpleMailMessage.setTo("1982-615@163.com");
        mailSender.send(simpleMailMessage);


    }

    @Test
    public void contextLoads2() throws MessagingException {
        //邮件设置2：一个复杂的邮件

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("通知-明天来狂神这听课");
        helper.setText("<b style='color:red'>今天 7:30来开会</b>", true);

        //发送附件
        helper.addAttachment("1.jpg", new File("/Users/I541634/Downloads/image003.jpg"));
        helper.addAttachment("2.jpg", new File("/Users/I541634/Downloads/image003.jpg"));

        helper.setTo("1982-615@163.com");
        helper.setFrom("1982-615@163.com");

        mailSender.send(mimeMessage);
    }

}
