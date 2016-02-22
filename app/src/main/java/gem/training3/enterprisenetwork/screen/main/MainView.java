package gem.training3.enterprisenetwork.screen.main;

import gem.training3.enterprisenetwork.network.dto.ResponseUserInfoDTO;

/**
 * Created by huylv on 22/02/2016.
 */
public interface MainView {
    void onGetCurrentUserSuccess(ResponseUserInfoDTO u);
    void onLogoutSuccess();
    void onLogoutError(String message);
    void onLogoutFailure(String message);
    void onNetworkError();
}
