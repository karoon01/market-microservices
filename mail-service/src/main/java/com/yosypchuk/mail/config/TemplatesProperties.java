package com.yosypchuk.mail.config;

import com.yosypchuk.mail.model.message.Event;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
public class TemplatesProperties {

    private List<Template> templates;

    @Data
    public static class Template {
        private String from;
        private String subject;
        private String template;

        private Event event;
    }
}
