package gem.training3.enterprisenetwork.screen.login;


import gem.training3.enterprisenetwork.base.BaseView;
import gem.training3.enterprisenetwork.network.dto.ResponseDTO;
import retrofit2.Response;

/**
 * Login views
 * Created by neo on 2/5/2016.
 */
public interface LoginView extends BaseView<LoginPresenter> {
    void onEmailError();
    void onPasswordError();
    void onLoginSuccess(Response<ResponseDTO> response);
    void onNetworkError();
}
