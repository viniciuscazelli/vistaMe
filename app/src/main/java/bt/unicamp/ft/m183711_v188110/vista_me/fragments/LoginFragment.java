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
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;

/**
 * A simple {@link Fragment} subclass.
 */

@SuppressLint("ValidFragment")
public class LoginFragment extends Fragment implements View.OnClickListener {

    private View fragment;
    private Button button;
    private EditText username;
    private EditText password;
    private TextView error;
    private Login login;
    private FragmentManagerActivity fragmentManagerActivity;

    public LoginFragment(Login login, FragmentManagerActivity fragmentManagerActivity) {
        this.login = login;
        this.fragmentManagerActivity = fragmentManagerActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_login, container, false);

        button = fragment.findViewById(R.id.loginButton);
        username = fragment.findViewById(R.id.username);
        password = fragment.findViewById(R.id.password);
        error = fragment.findViewById(R.id.message);
        button.setOnClickListener(this);

        return fragment;
    }

    @Override
    public void onClick(View v) {

        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        if(login.validLogin(username.getText().toString(),password.getText().toString())){
            fragmentManagerActivity.RemoveFragment(this);
        }else{
            error.setText("Usuário e/ou senha incorreto(s)");
        }
    }
}
