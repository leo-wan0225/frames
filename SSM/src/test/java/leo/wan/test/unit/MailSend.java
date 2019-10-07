package leo.wan.test.unit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import leo.wan.test.base.BaseJunit4Test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 发送邮件的一个demo
 *
 * @author Administrator
 */
public class MailSend extends BaseJunit4Test {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TaskExecutor taskExecutor;

    @Test
    public void testSend() throws InterruptedException {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom("wjing225@163.com");
            //设置多人收件时可以用字符串数组设置
            helper.setTo(new String[]{"961241196@qq.com", "2793497399@qq.com"});
            helper.setSubject("ces");
            //设置为true表示内容是html
            helper.setText("2232<br/>换行了", true);
            //可以添加附件
            helper.addAttachment("cd", new ClassPathResource("/jdbc.properties"));
            addSendMailTask(message);
            Thread.currentThread().sleep(1000);
            System.out.println("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private void addSendMailTask(final MimeMessage message) {
        try {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    javaMailSender.send(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
