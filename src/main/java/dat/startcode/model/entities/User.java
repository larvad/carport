package dat.startcode.model.entities;

import java.util.Objects;

public class User
{

    private String username;
    private String password;
    private int roleId;
    private int userId;
    private String email;
    private int phoneNr;
    private String adress;


    public User(String username, String password, int roleId, int userId, String email, int phoneNr, String adress) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.userId = userId;
        this.email = email;
        this.phoneNr = phoneNr;
        this.adress = adress;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = this.adress;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) &&
                getRoleId() == (user.getRoleId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUsername(), getPassword(), getRoleId());
    }
}
