package com.zmarket.marketadminservice.modules.security;

import com.zmarket.marketadminservice.exceptions.UnathorizedException;
import com.zmarket.marketadminservice.modules.security.model.User;
import com.zmarket.marketadminservice.util.RestUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

   private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);

   private final RestUtil restUtil;

   @Override
   @Transactional
   public UserDetailsImpl loadUserByUsername(final String token) {
      log.debug("Authenticating user '{}'", token);

      User user = restUtil.getUserByToken(token);
      if (Objects.isNull(user)) {
         throw new UsernameNotFoundException("User not found");
      } else {
         if(!user.isActivated())
            throw new UnathorizedException("Account not activated");
      }
      return UserDetailsImpl.build(user);
   }
}
