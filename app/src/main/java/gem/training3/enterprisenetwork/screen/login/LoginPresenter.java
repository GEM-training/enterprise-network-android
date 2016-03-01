package gem.training3.enterprisenetwork.screen.login;


import gem.training3.enterprisenetwork.base.BasePresenter;

/**
 * Login presenter
 * Created by neo on 2/5/2016.
 */
public interface LoginPresenter extends BasePresenter {
    void doLogin(String email, String password, String deviceId);

}
