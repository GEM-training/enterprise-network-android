package gem.training3.enterprisenetwork.screen.main;

import android.content.Context;

import gem.training3.enterprisenetwork.base.log.Logger;
import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.dto.ResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 22/02/2016.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void getCurrentUser() {
        view.onGetCurrentUserSuccess(Session.getCurrentUser());
    }

    @Override
    public void doLogout(final Context c) {
        Call<ResponseDTO> call = ServiceBuilder.getService().logout(Session.getCurrentUser().getToken());
        call.enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if (response.isSuccess()) {

                    Session.removeUser();
                    c.getSharedPreferences(Constants.USER_INFO, c.MODE_PRIVATE).edit().clear().commit();

                    view.onLogoutSuccess();
                }
                else {
                    Logger.d("network not connect when logout");
                    view.onLogoutSuccess();
//                    view.onLogoutError(((ResponseDTO) response.body()).getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
//                view.onLogoutError(t.getMessage());
                view.onLogoutSuccess();
                Logger.d("network not connect when logout");
            }
        });
    }

}
