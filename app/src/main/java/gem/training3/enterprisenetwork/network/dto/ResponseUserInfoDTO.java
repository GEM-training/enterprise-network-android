package gem.training3.enterprisenetwork.network.dto;

import java.util.List;

import gem.training3.enterprisenetwork.base.BaseDTO;

/**
 * Created by huylv on 19/02/2016.
 */
public class ResponseUserInfoDTO  extends BaseDTO {
    String username;
    String token;
    List<String> role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "ResponseUserInfoDTO{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", role=" + role +
                '}';
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
