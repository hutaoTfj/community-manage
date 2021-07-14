package ink.hutao.manage.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

/**
 * <p>邮箱验证码</p>
 * @author tfj
 * @since 2021/7/13
 */
@Configuration
public class MailConfig {

    @Value("${spring.mail.username}")
    private String userName;

    @Bean
    public SimpleMailMessage sendMail(){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom(userName);
        simpleMailMessage.setSubject("智慧社区");
        return simpleMailMessage;
    }

}
