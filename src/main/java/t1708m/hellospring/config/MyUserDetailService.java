package t1708m.hellospring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import t1708m.hellospring.entity.Account;
import t1708m.hellospring.service.AccountService;

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountService.getByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(account.getEmail())
                .password(account.getPassword())
                .roles("admin", "employee", "user")
                .build();
    }
}
