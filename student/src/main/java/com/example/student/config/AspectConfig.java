package com.example.student.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableAspectJAutoProxy
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AspectConfig {
}
