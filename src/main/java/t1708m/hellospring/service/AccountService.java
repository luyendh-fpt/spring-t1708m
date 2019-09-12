package t1708m.hellospring.service;

import t1708m.hellospring.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getList();

    Account getDetail(String email);

    // Thực hiện xác thực người dùng.
    Account login(String email, String password);

    // Đăng ký tài khoản, mã hoá mật khẩu...
    Account register(Account account);

    // Update thông tin tài khoản theo email.
    Account update(String email, Account account);
}
