package com.filarty.zoomarket.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class EmailServiceBody {
    public static String confirmEmail(String username, String token) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setClassForTemplateLoading(EmailService.class, "/templates");
        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        Template template = configuration.getTemplate("confirm_email.ftlh");
        StringWriter writer = new StringWriter();
        template.process(model, writer);
        return writer.toString();

    }
}
