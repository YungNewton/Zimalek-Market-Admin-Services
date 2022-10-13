package com.zmarket.marketadminservice.modules.security.jwt;

import com.zmarket.marketadminservice.modules.security.UserDetailsImpl;
import com.zmarket.marketadminservice.modules.security.UserModelDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {

   private static final Logger LOG = LoggerFactory.getLogger(JWTFilter.class);

   public static final String AUTHORIZATION_HEADER = "Authorization";

   private TokenProvider tokenProvider;

   private UserModelDetailsService userModelDetailsService;

   public JWTFilter(TokenProvider tokenProvider, UserModelDetailsService userModelDetailsService) {
      this.tokenProvider = tokenProvider;
      this.userModelDetailsService = userModelDetailsService;
   }

   @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
      String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJva2l0b29naGVuZXRlZ2FAZ21haWwuY29tIiwiYXV0aCI6IlJPTEVfQlJBTkRfU0VMTEVSLFJPTEVfVVNFUiIsImV4cCI6MTY2NTcyNjU1Nn0.teEZaUSBnrRiySUcu9utrvO88kWg9TcYelfgqfhzD04fY0xPJkk1VLVJUNJY6vWcoigzTx1UnqGCqlG_ARD8jw";
      String requestURI = httpServletRequest.getRequestURI();

      if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
         UserDetailsImpl userDetails = userModelDetailsService.loadUserByUsername(jwt);
         UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
         authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
         SecurityContextHolder.getContext().setAuthentication(authentication);
         tokenProvider.setUserDetails(userDetails);
         LOG.debug("set Authentication to security context for '{}', uri: {}", userDetails.getEmail(), requestURI);
      } else {
         LOG.debug("no valid JWT token found, uri: {}", requestURI);
      }

      filterChain.doFilter(servletRequest, servletResponse);

   }

   private String resolveToken(HttpServletRequest request) {
      String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
      if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
         return bearerToken.substring(7);
      }
      return null;
   }
}
