package lk.ijse.pharamacymanagementlayerdsystem.entity;

public class User {
    private Long userId;
    private String username;
    private String password;
    private String fullName;
    private String contact;
    private String role;


    public User() {
    }

    public User(String username, String password, String fullName, String contact, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.contact = contact;
        this.role = role;

    }

    public User(Long userId, String username, String password, String fullName, String contact, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.contact = contact;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contact='" + contact + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
