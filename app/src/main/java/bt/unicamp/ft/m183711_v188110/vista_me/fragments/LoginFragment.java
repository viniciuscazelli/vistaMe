package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bt.unicamp.ft.m183711_v188110.vista_me.Login;
import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DbExecuteWithReturn;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;

/**
 * A simple {@link Fragment} subclass.
 */

@SuppressLint("ValidFragment")
public class LoginFragment extends Fragment implements View.OnClickListener {

    private View fragment;
    private Button button;
    private Button buttonRegister;
    private EditText username;
    private EditText password;
    private TextView error;
    private Login login;
    private FragmentManagerActivity fragmentManagerActivity;
    private Fragment nextFragment;
    private Fragment thisFragment = this;

    public LoginFragment(Login login, FragmentManagerActivity fragmentManagerActivity,Fragment nextFragment) {
        this.login = login;
        this.fragmentManagerActivity = fragmentManagerActivity;
        this.nextFragment= nextFragment;
    }

    public LoginFragment(Login login, FragmentManagerActivity fragmentManagerActivity) {
        this.login = login;
        this.fragmentManagerActivity = fragmentManagerActivity;
        this.nextFragment= null;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_login, container, false);

        button = fragment.findViewById(R.id.loginButton);
        buttonRegister = fragment.findViewById(R.id.registerButton);
        username = fragment.findViewById(R.id.username);
        password = fragment.findViewById(R.id.password);
        error = fragment.findViewById(R.id.message);
        button.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        return fragment;
    }

    @Override
    public void onClick(View v) {

        Button b = (Button) v;

        if(b.getId() == R.id.registerButton) {
            fragmentManagerActivity.RemoveFragment(this);
            RegisterFragment registerFragment = new RegisterFragment(login,fragmentManagerActivity,nextFragment);
            fragmentManagerActivity.OpenFragment(registerFragment,"Register",true);
            return;
        }

        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        login.validLogin(username.getText().toString(), password.getText().toString(), new DbExecuteWithReturn() {
            @Override
            public void onReturn(Object obj) {
                if((boolean)obj){
                    fragmentManagerActivity.RemoveFragment(thisFragment);
                    if(nextFragment != null)
                        fragmentManagerActivity.OpenFragment(nextFragment,"redirectFragment",true);
                }else{
                    error.setText("Usuário e/ou senha incorreto(s)");
                }
            }
        });

    }
}
