package cn.edu.yulinu.controller;

import cn.edu.yulinu.entity.TbUser;
import cn.edu.yulinu.service.TbUserService;
import cn.edu.yulinu.tools.CommonResult;
import cn.edu.yulinu.tools.VerCodeGenerateUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("mail")
@Api(tags = "邮箱验证")
@CrossOrigin
public class SendMailController {
    // 创建线程池对象
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    // 这个是我用户Service实现类可以自行替换
    @Autowired
    TbUserService tbUserService;
    // 这个是邮件类，必须要导入哦
    @Autowired
    JavaMailSenderImpl mailSender;

    @GetMapping("/sendEmail")
    public CommonResult sendEmail(String email, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 验证码
        String verCode = VerCodeGenerateUtil.getVerCode();
        // 发送时间 --这里自己写了一个时间类，格式转换，用于邮件发送
        // String time = DateUtils.date2String(new Date(), "yyyy-MM-dd hh:mm:ss");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:dd:ss").format(new Date());
        Map<String, String> map = new HashMap<>();
        map.put("code", verCode);
        map.put("email", email);
        // 验证码和邮箱，一起放入session中
        session.setAttribute("verCode", map);
        Map<String, String> codeMap = (Map<String, String>) session.getAttribute("verCode");
        // 创建计时线程池，到时间自动移除验证码
        try {
            scheduledExecutorService.schedule(new Thread(() -> {
                if (email.equals(codeMap.get("email"))) {
                    session.removeAttribute("verCode");
                }
            }), 5 * 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 一下为发送邮件部分
        MimeMessage mimeMessage = null;
        MimeMessageHelper helper = null;
        try {
            // 发送复杂的邮件
            mimeMessage = mailSender.createMimeMessage();
            // 组装
            helper = new MimeMessageHelper(mimeMessage, true);
            // 邮件标题
            helper.setSubject("【物联网监控中心】 注册账号验证码");
            // 因为设置了邮件格式所以html标签有点多，后面的ture为支持识别html标签
            // 想要不一样的邮件格式，百度搜索一个html编译器，自我定制。
            helper.setText("<h3>\n" +
                    "\t<span style=\"font-size:16px;\">亲爱的用户：</span> \n" +
                    "</h3>\n" +
                    "<p>\n" +
                    "\t<span style=\"font-size:14px;\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=\"font-size:14px;\">&nbsp; <span style=\"font-size:16px;\">&nbsp;&nbsp;您好！您正在进行邮箱验证，本次请求的验证码为：<span style=\"font-size:24px;color:#FFE500;\"> " + verCode + "</span>,本验证码5分钟内有效，请在5分钟内完成验证。（请勿泄露此验证码）如非本人操作，请忽略该邮件。(这是一封自动发送的邮件，请不要直接回复）</span></span>\n" +
                    "</p>\n" +
                    "<p style=\"text-align:right;\">\n" +
                    "\t<span style=\"background-color:#FFFFFF;font-size:16px;color:#000000;\"><span style=\"color:#000000;font-size:16px;background-color:#FFFFFF;\"><span class=\"token string\" style=\"font-family:&quot;font-size:16px;color:#000000;line-height:normal !important;background-color:#FFFFFF;\">物联网监控中心</span></span></span> \n" +
                    "</p>\n" +
                    "<p style=\"text-align:right;\">\n" +
                    "\t<span style=\"background-color:#FFFFFF;font-size:14px;\"><span style=\"color:#FF9900;font-size:18px;\"><span class=\"token string\" style=\"font-family:&quot;font-size:16px;color:#000000;line-height:normal !important;\"><span style=\"font-size:16px;color:#000000;background-color:#FFFFFF;\">" + time + "</span><span style=\"font-size:18px;color:#000000;background-color:#FFFFFF;\"></span></span></span></span> \n" +
                    "</p>", true);
            // 收件人
            helper.setTo(email);
            // 发送方
            helper.setFrom("2646513007@qq.com");
            try {
                // 发送邮件
                mailSender.send(mimeMessage);
            } catch (MailException e) {
                // 邮箱是无效的，或者发送失败
                return CommonResult.failed("邮箱是无效的，或者发送失败");
            }
        } catch (MessagingException e) {
            // 发送失败--服务器繁忙
            return CommonResult.failed("发送失败--服务器繁忙");
        }
        // 发送验证码成功
        return CommonResult.success("发送验证码成功");
    }

    // 这里我采用的是email作为username
    @PostMapping("/reg")
    @ResponseBody
    public CommonResult reg(@RequestBody TbUser tbUser,
                            String newUserMail,
                            String verCode,
                            HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Map<String, String> codeMap = (Map<String, String>) session.getAttribute("verCode");
        String code = null;
        String email = null;
        try {
            code = codeMap.get("code");
            // email = codeMap.get("email");
        } catch (Exception e) {
            // 验证码过期，或未找到  ---验证码无效
            return CommonResult.failed("验证码过期，或未找到");
        }
        // 验证码判断
        if (!verCode.toUpperCase().equals(code)) {
            return CommonResult.failed("验证码错误");
        }
        // 验证码使用完后session删除
        session.removeAttribute("verCode");
        tbUser.setUserMail(newUserMail);
        tbUserService.updateMail(tbUser);
        return CommonResult.success("修改邮箱成功");
    }


}
