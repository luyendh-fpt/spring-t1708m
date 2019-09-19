package t1708m.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import t1708m.hellospring.entity.Account;
import t1708m.hellospring.repository.AccountRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImplement implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Page<Account> getList(int page, int limit) {
        return accountRepository.findAll(PageRequest.of(page - 1, limit));
    }

    @Override
    public Account getDetail(String email) {
        return accountRepository.findById(email).orElse(null);
    }

    @Override
    public Account login(String email, String password) {
        // Tìm tài khoản có email trùng xem tồn tại không.
        Optional<Account> optionalAccount = accountRepository.findById(email);
        if (optionalAccount.isPresent()) {
            // So sánh password xem trùng không (trong trường hợp pwd đã mã hoá thì phải mã hoá pwd truyền vào theo muối)
            Account account = optionalAccount.get();
            if (account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Account register(Account account) {
        // thực hiện mã hoá password nếu cần.
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setCreatedAtMLS(Calendar.getInstance().getTimeInMillis());
        account.setUpdatedAtMLS(Calendar.getInstance().getTimeInMillis());
        account.setStatus(1);
        return accountRepository.save(account);
    }

    @Override
    public Account update(String email, Account updateAccount) {
        // Tìm tài khoản có email trùng xem tồn tại không.
        Optional<Account> optionalAccount = accountRepository.findById(email);
        if (optionalAccount.isPresent()) {
            Account existAccount = optionalAccount.get();
            existAccount.setFullName(updateAccount.getFullName());
            existAccount.setPhone(updateAccount.getPhone());
            existAccount.setAddress(updateAccount.getAddress());
            existAccount.setUpdatedAtMLS(Calendar.getInstance().getTimeInMillis());
            return accountRepository.save(existAccount);
        }
        return null;
    }

    @Override
    public Account getByEmail(String email) {
        return accountRepository.findById(email).orElse(null);
    }
}
