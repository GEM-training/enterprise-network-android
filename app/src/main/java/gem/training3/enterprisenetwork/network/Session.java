package gem.training3.enterprisenetwork.network;


import gem.training3.enterprisenetwork.network.model.UserCredential;

/**
 * Created by huylv on 19/02/2016.
 */
public class Session {
    private static UserCredential user;

    public static void setUser(UserCredential user) {
        Session.user=user;
    }

    public static void removeUser(){
        if(user!=null){
            user =null;
        }
    }

    public static UserCredential getCurrentUser(){
        return user;
    }
}