package com.crinity.common.security;

import org.springframework.lang.Nullable;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.function.Supplier;

@Component
public class MatchAuthenticationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final SecurityProperty securityProperty;

    public MatchAuthenticationManager(SecurityProperty securityProperty) {
        Assert.hasText(securityProperty.resourceId(), "security.resource-id 설정된 값이 없습니다. 설정 파일을 확인하세요.");
        this.securityProperty = securityProperty;
    }

    @Nullable
    @Override
    public AuthorizationDecision check(
            Supplier<Authentication> authentication,
            RequestAuthorizationContext context
    ) {
        Authentication auth = authentication.get();
        if (Objects.isNull(auth) || !auth.isAuthenticated()) return new AuthorizationDecision(false);

        boolean isAllowScope = auth.getAuthorities().stream()
                .anyMatch(scope -> scope
                        .getAuthority().equals(securityProperty.resourceId())
                );

        return new AuthorizationDecision(isAllowScope);
    }
}
