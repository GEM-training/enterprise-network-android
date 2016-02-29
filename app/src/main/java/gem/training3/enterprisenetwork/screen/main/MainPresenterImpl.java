package gem.training3.enterprisenetwork.screen.main;

import android.content.Context;

import gem.training3.enterprisenetwork.base.log.Logger;
import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 22/02/2016.
 */
public class MainPresenterImpl implements MainPresenter {
    private final MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void doLogout(final Context c) {
        Call<Void> call = ServiceBuilder.getService().logout(Session.getCurrentUser().getToken(),Session.getCurrentUser().getDeviceId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccess()) {
                    Session.removeUser();
                    c.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE).edit().clear().commit();
                    view.onLogoutSuccess();
                }
                else {
                    Logger.e("Network not connect when logout1");
                    view.onLogoutSuccess();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.onLogoutSuccess();
                Logger.e("Network not connect when logout2");
            }
        });
    }

}
