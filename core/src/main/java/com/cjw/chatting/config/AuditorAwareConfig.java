package com.cjw.chatting.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.springframework.util.ObjectUtils.*;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Component
@EnableJpaAuditing
@Slf4j
public class AuditorAwareConfig implements AuditorAware<String> {
    private static final String HEADER = "user-name";
    private static final String UNKNOWN = "unknown";

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) currentRequestAttributes()).getRequest();
            String user = request.getHeader(HEADER);

            if (isEmpty(user)) {
                user = UNKNOWN;
            }

            return Optional.of(user);
        } catch (Exception e) {
            return Optional.of(UNKNOWN);
        }
    }
}

