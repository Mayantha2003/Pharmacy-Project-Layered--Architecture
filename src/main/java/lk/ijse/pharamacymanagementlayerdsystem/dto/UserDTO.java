package lk.ijse.pharamacymanagementlayerdsystem.dto;

public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private String fullName;
    private String contact;
    private String role;


    public UserDTO() {
    }

    public UserDTO(String username, String password, String fullName, String contact, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.contact = contact;
        this.role = role;

    }

    public UserDTO(Long userId, String username, String password, String fullName, String contact, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.contact = contact;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contact='" + contact + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
