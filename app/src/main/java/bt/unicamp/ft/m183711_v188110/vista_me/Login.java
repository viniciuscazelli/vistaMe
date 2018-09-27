package bt.unicamp.ft.m183711_v188110.vista_me;

import bt.unicamp.ft.m183711_v188110.vista_me.entities.User;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.LoginListener;

public class Login {

    private User user;
    private LoginListener loginListener;

    public Login(LoginListener loginListener){
        this.loginListener = loginListener;
    }

    public Boolean validLogin(String Username, String Password){
        if(Username.equals("FT") && Password.equals("12345")){
            this.user = new User(1,"Faculdade","Tecnologia","00000000000",
                    "R. Paschoal Marmo","1888","Jardim Sao Paulo","Limeira","São Paulo","13484-332",
                    "FT","12345",null,null);
            if(loginListener != null)
                loginListener.changeLoginStatus();
            return true;
        }else{
            return false;
        }
    }

    public String register(String name, String lastname,String CPF,String address,String number,
                           String distric,String city,String country,String CEP,String username,
                           String password,String passwordConfirm){
        if(isLogged()){
            return "Não é possivel se registar pois um usuário esta logado no momento neste dispositivo.";
        }else if(name.isEmpty() || lastname.isEmpty() || CPF.isEmpty() || address.isEmpty() &&
                number.isEmpty() || distric.isEmpty()|| city.isEmpty()|| country.isEmpty()&&
                CEP.isEmpty()|| username.isEmpty()|| password.isEmpty() || passwordConfirm.isEmpty()){
            return "Preencha os campos corretamente";
        }else if(!password.equals(passwordConfirm)){
            return "As senhas não conferem";
        }

            user = new User(-1,name,lastname,CPF,address,number,distric,city,country,CEP,username,password,null,null);
            return "";


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
