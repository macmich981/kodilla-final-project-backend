package com.kodilla.carsbackend.service;

import com.kodilla.carsbackend.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    public String buildRentInfoEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview_message", "Info about rents");
        context.setVariable("cars_url", "http://localhost:8080");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());
        return templateEngine.process("mail/info-about-rents-mail", context);
    }
}
