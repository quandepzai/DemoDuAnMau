package com.example.noname.demoduanmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noname.demoduanmau.sqlitedao.UserDAO;
import com.example.noname.demoduanmau.database.DataBaseHelper;
import com.example.noname.demoduanmau.model.User;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {
    private ProgressBar loginProgress;
    private LinearLayout emailLoginForm;
    private AutoCompleteTextView email;
    private EditText password;
    private Button emailSignInButton;
    private TextView tvDoimatkhau;

    private DataBaseHelper dataBaseHelper;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        dataBaseHelper = new DataBaseHelper(this);
        userDAO = new UserDAO(dataBaseHelper);
//        for (int i=0; i<20; i++){
            User user = new User("admin" ,"admin123","Quan Nguyen","0961212253");
            userDAO.insertUser(user);
       // }

        initView();


        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = email.getText().toString().trim();
                String matkhau = password.getText().toString().trim();

                if (taikhoan.equals("")){
                    email.setError(getString(R.string.noi));
                }
                if (matkhau.equals("")){
                    password.setError(getString(R.string.loi));
                }

                else {
                    User user = userDAO.getUserByUsername(taikhoan);
                    //neu suer != null, tuc la user co tren DB thi so sanh password
                    if (user !=null){
                        String passwordOnDatabase = user.getPassword();

                        //neu password giong nhau thi cho phep dang nhap va mo man hinh MainActivity
                        if (passwordOnDatabase.equals(matkhau)){
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        //neu password k giong thi thong bao loi
                        else Toast.makeText(LoginActivity.this,getString(R.string.notify_wrong_password), Toast.LENGTH_SHORT).show();
                    }
                    //neu user == null thi thong bao loi
                    else Toast.makeText(LoginActivity.this,getString(R.string.notify_wrong_password), Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvDoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initView() {
        tvDoimatkhau = findViewById(R.id.tvDoimatkhau);
        loginProgress =  findViewById(R.id.login_progress);
        emailLoginForm =  findViewById(R.id.email_login_form);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        emailSignInButton = findViewById(R.id.email_sign_in_button);
    }
}

