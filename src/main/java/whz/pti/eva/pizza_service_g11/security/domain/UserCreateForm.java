package whz.pti.eva.pizza_service_g11.security.domain;
import javax.validation.constraints.NotEmpty;


public class UserCreateForm {

    @NotEmpty
    private String nickname = "";

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String lastName = "";

    @NotEmpty
    private String password = "";

    @NotEmpty()
    private String passwordRepeated = "";


    //    @NotNull
    private Role role = Role.USER;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserCreateForm{" +
                "email='" + email.replaceFirst("@.+", "@***") + '\'' +
                ", password=***" + '\'' +
                ", passwordRepeated=***" + '\'' +
                ", role=" + role +
                ", nickname=" + nickname +
                ", name=" + name +
                ", lastname=" + lastName +
                '}';

    }


}