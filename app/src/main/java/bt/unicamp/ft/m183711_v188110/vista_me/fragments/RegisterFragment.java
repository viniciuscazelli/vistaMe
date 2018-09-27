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
import android.widget.Spinner;
import android.widget.TextView;

import bt.unicamp.ft.m183711_v188110.vista_me.Login;
import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private View fragment;
    private Login login;
    private EditText name;
    private EditText lastname;
    private EditText CPF;
    private EditText address;
    private EditText number;
    private EditText distric;
    private EditText city;
    private EditText CEP;
    private EditText username;
    private EditText password;
    private EditText passwordConfirm;
    private Spinner country;
    private TextView message;
    private Button registerButton;

    private FragmentManagerActivity fragmentManagerActivity;

    @SuppressLint("ValidFragment")
    public RegisterFragment(Login login, FragmentManagerActivity fragmentManagerActivity) {
        this.login = login;
        this.fragmentManagerActivity = fragmentManagerActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_register, container, false);

        name = fragment.findViewById(R.id.name);
        lastname= fragment.findViewById(R.id.lastname);
        CPF= fragment.findViewById(R.id.CPF);
        address= fragment.findViewById(R.id.address);
        number= fragment.findViewById(R.id.number);
        distric= fragment.findViewById(R.id.distric);
        city= fragment.findViewById(R.id.city);
        country= fragment.findViewById(R.id.country);
        CEP= fragment.findViewById(R.id.CEP);
        username= fragment.findViewById(R.id.username);
        password= fragment.findViewById(R.id.password);
        passwordConfirm= fragment.findViewById(R.id.passwordConfirm);
        message= fragment.findViewById(R.id.message);
        registerButton= fragment.findViewById(R.id.registerButton);

        registerButton.setOnClickListener(this);

        return fragment;
    }

    @Override
    public void onClick(View v) {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        String r = login.register(
                name.getText().toString(),
                lastname.getText().toString(),
                CPF.getText().toString(),
                address.getText().toString(),
                number.getText().toString(),
                distric.getText().toString(),
                city.getText().toString(),
                country.getSelectedItem().toString(),
                CEP.getText().toString(),
                username.getText().toString(),
                password.getText().toString(),
                passwordConfirm.getText().toString()
                );

        if(r.isEmpty()){
            fragmentManagerActivity.RemoveFragment(this);
        }else{
            message.setText(r);
        }
    }
}
