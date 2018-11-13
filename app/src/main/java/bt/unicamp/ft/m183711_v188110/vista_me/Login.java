package bt.unicamp.ft.m183711_v188110.vista_me;

import bt.unicamp.ft.m183711_v188110.vista_me.database.Users;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.User;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DBExecute;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DbExecuteWithReturn;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.LoginListener;

public class Login {

    private User user;
    private LoginListener loginListener;

    public Login(LoginListener loginListener){
        this.loginListener = loginListener;
    }

    public Boolean validLogin(String Username, String Password, final DbExecuteWithReturn dbExecuteWithReturn){

        Users.validLogin(new DbExecuteWithReturn() {
            @Override
            public void onReturn(Object obj) {
                if(obj == null){
                    dbExecuteWithReturn.onReturn(false);
                }else{
                    user = (User)obj;
                    dbExecuteWithReturn.onReturn(true);
                }
                loginListener.changeLoginStatus();
            }
        },Username,Password);

        return false;
    }

    public User getUser() {
        return user;
    }

    public void register(final String name, final String lastname, final String CPF, final String address, final String number,
                           final String distric, final String city, final String country, final String CEP, final String username,
                           final String password, String passwordConfirm, final String sexo, final DbExecuteWithReturn dbExecuteWithReturn){
         if(name.isEmpty() || lastname.isEmpty() || CPF.isEmpty() || address.isEmpty() ||
                number.isEmpty() || distric.isEmpty()|| city.isEmpty()|| country.isEmpty() ||
                CEP.isEmpty()|| username.isEmpty()|| password.isEmpty() || passwordConfirm.isEmpty()||
                sexo.isEmpty()){
            dbExecuteWithReturn.onReturn("Preencha os campos corretamente");
        }else if(!password.equals(passwordConfirm)){
            dbExecuteWithReturn.onReturn( "As senhas não conferem");
        }

        Users.checkedUserNameRegisted(new DbExecuteWithReturn() {
            @Override
            public void onReturn(Object obj) {

                if(isLogged()) {

                    Users.updateUser(new DBExecute() {
                        @Override
                        public void onReturn() {
                            user = new User(getUser().getId(),name,lastname,CPF,address,number,distric,city,country,CEP,username,password,null,null,sexo);
                            loginListener.changeLoginStatus();
                            dbExecuteWithReturn.onReturn("");
                        }
                    },getUser().getId(),name,lastname,CPF,address,number,distric,city,country,CEP,username,password,sexo);

                }else if((boolean)obj){
                    dbExecuteWithReturn.onReturn("Username já cadastrado");
                }else{
                    Users.checkedCPFRegisted(new DbExecuteWithReturn() {
                        @Override
                        public void onReturn(Object obj) {
                            if((boolean)obj){
                                dbExecuteWithReturn.onReturn("CPF já cadastrado");
                            }else{
                                Users.createUser(new DbExecuteWithReturn() {
                                    @Override
                                    public void onReturn(Object obj) {
                                        user = new User((String)obj,name,lastname,CPF,address,number,distric,city,country,CEP,username,password,null,null,sexo);
                                        loginListener.changeLoginStatus();
                                        dbExecuteWithReturn.onReturn("");
                                    }
                                },name,lastname,CPF,address,number,distric,city,country,CEP,username,password,sexo);
                            }
                        }
                    },CPF);
                }

            }
        },username);
    }

    public Boolean isLogged(){
        return user != null;
    }

    public void loggout(){
        user = null;
        if(loginListener != null)
            loginListener.changeLoginStatus();
    }


}
