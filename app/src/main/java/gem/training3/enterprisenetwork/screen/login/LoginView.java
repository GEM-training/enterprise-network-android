package gem.training3.enterprisenetwork.screen.login;


import gem.training3.enterprisenetwork.base.BaseView;

/**
 * Login views
 * Created by neo on 2/5/2016.
 */
public interface LoginView extends BaseView<LoginPresenter> {
    void onPasswordError();
    void onLoginSuccess(String json);
}
