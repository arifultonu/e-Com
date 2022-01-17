package com.ecom.jwtauthservice.jwt;


import com.ecom.jwtauthservice.setup.entity.SetupUserEntity;
import com.ecom.jwtauthservice.setup.repository.SetupUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JwtInMemoryUserDetailsService implements UserDetailsService {
    @Autowired
    private SetupUserRepo setupUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
        SetupUserEntity setupUserEntity = setupUserRepo.findByUserName(username);

        if (setupUserEntity != null) {
            if (setupUserEntity.getUsername() != "" && setupUserEntity.getPassword() != null) {
                inMemoryUserList.add(new JwtUserDetails(setupUserEntity.getId(), setupUserEntity.getUsername(),
                        setupUserEntity.getPassword(), setupUserEntity.getRole()));
            } else{
                throw new UsernameNotFoundException(String.format("USER/PASSWORD_NOT_FOUND FOR '%s'.", username));
            }
        }
        Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
                .filter(user -> user.getUsername().equals(username)).findFirst();

        if (!findFirst.isPresent()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return findFirst.get();

    }


}
