package auth;

public class FakeAuthService {

    /**
     * Mô phỏng kiểm tra đăng nhập:
     * teacher / 123  → Giáo viên
     * student / 123  → Sinh viên
     */
    public static String login(String username, String password) {

        if (username.equals("teacher") && password.equals("123")) {
            return "SUCCESS;TEACHER";
        }

        if (username.equals("student") && password.equals("123")) {
            return "SUCCESS;STUDENT";
        }

        return "FAIL";
    }
}
