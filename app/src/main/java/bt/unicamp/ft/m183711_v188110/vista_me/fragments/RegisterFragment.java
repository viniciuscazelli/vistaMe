package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import bt.unicamp.ft.m183711_v188110.vista_me.Login;
import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.User;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DbExecuteWithReturn;
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
    private Button loginButton;
    private RadioGroup groupSexo;
    private RadioButton radio_m;
    private RadioButton radio_f;

    private Fragment nextFragment;
    private Fragment thisFragment = this;


    private FragmentManagerActivity fragmentManagerActivity;

    @SuppressLint("ValidFragment")
    public RegisterFragment(Login login, FragmentManagerActivity fragmentManagerActivity) {
        this.login = login;
        this.fragmentManagerActivity = fragmentManagerActivity;
        this.nextFragment= null;
    }

    @SuppressLint("ValidFragment")
    public RegisterFragment(Login login, FragmentManagerActivity fragmentManagerActivity,Fragment nextFragment) {
        this.login = login;
        this.fragmentManagerActivity = fragmentManagerActivity;
        this.nextFragment = nextFragment;
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
        loginButton= fragment.findViewById(R.id.loginButton);
        groupSexo = fragment.findViewById(R.id.groupSexo);
        radio_m = fragment.findViewById(R.id.radio_m);
        radio_f = fragment.findViewById(R.id.radio_f);

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);


        if(login.isLogged()){
            loadData();
            loginButton.setVisibility(View.INVISIBLE);
            registerButton.setText("Salvar");
        }


        return fragment;
    }

    public void loadData(){
        User user = login.getUser();

        name.setText(user.getName());
        lastname.setText(user.getLastname());
        CPF.setText(user.getCPF());
        address.setText(user.getAddress());;
        number.setText(user.getNumber());
        distric.setText(user.getDistric());
        city.setText(user.getCity());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.estados, android.R.layout.simple_spinner_item);
        int spinnerPosition = adapter.getPosition(user.getCountry());
        country.setSelection(spinnerPosition);

        CEP.setText(user.getCEP());
        username.setText(user.getUsername());
        password.setText(user.getPassword());
        passwordConfirm.setText(user.getPassword());

        radio_f.setSelected(user.getSexo() == "Feminino");
        radio_m.setSelected(user.getSexo() == "Masculino");

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.loginButton)
        {
            fragmentManagerActivity.RemoveFragment(this);
            LoginFragment loginFragment = new LoginFragment(login,fragmentManagerActivity,nextFragment);
            fragmentManagerActivity.OpenFragment(loginFragment,"login",true);

        }


        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        login.register(

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
                passwordConfirm.getText().toString(),
                ((RadioButton) fragment.findViewById(groupSexo.getCheckedRadioButtonId())).getText().toString(),
                new DbExecuteWithReturn() {
                    @Override
                    public void onReturn(Object obj) {

                        String r = (String)obj;

                        if(r.isEmpty()){
                            fragmentManagerActivity.RemoveFragment(thisFragment);
                            if(nextFragment != null)
                                fragmentManagerActivity.OpenFragment(nextFragment,"redirectFragment",true);
                        }else{
                            message.setText(r);
                        }
                    }
                }
        );


    }
}
