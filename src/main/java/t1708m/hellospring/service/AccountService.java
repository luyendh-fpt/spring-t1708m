package t1708m.hellospring.service;

import org.springframework.data.domain.Page;
import t1708m.hellospring.entity.Account;

import java.util.List;

public interface AccountService {

    Page<Account> getList(int page, int limit);

    Account getDetail(String email);

    // Thực hiện xác thực người dùng.
    Account login(String email, String password);

    // Đăng ký tài khoản, mã hoá mật khẩu...
    Account register(Account account);

    // Update thông tin tài khoản theo email.
    Account update(String email, Account account);

    Account getByEmail(String email);
}
