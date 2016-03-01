package gem.training3.enterprisenetwork.network.model;

public class UserCredential {

    private String username;
    private String password;
    private String token;
    private String deviceId;

    public UserCredential(String username, String password, String token, String deviceId) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.deviceId = deviceId;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
