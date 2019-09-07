package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailCreatorService {
    private final AdminConfig adminConfig;
    private final CompanyConfig companyConfig;

    @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tusks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context= new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://aniaxs.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("is_friend", true);
        context.setVariable("goodbye_message", "Hope to see you soon");
        context.setVariable("company_details", companyConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDatabaseEmail(String message) {
        Context context= new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://aniaxs.github.io/");
        context.setVariable("button", "View tasks");
        context.setVariable("show_button", true);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("is_friend", false);
        context.setVariable("company_details", companyConfig);
        return templateEngine.process("mail/trello-tasks-mail", context);
    }
}
