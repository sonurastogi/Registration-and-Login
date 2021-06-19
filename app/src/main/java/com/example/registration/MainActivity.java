package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button register, log_in;
    EditText First_Name, Last_Name, Email, Password ;
    String F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder;
    String finalResult ;
    String HttpURL = "http://webtgroup.online/Mysql/Registeration/UserRegistration.php";
    Boolean CheckEditText ;



    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Id'S
        First_Name = (EditText)findViewById(R.id.editTextF_Name);
        Last_Name = (EditText)findViewById(R.id.editTextL_Name);
        Email = (EditText)findViewById(R.id.editTextEmail);
        Password = (EditText)findViewById(R.id.editTextPassword);

        register = (Button)findViewById(R.id.Submit);
        log_in = (Button)findViewById(R.id.Login);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.
                    //Toast.makeText(MainActivity.this, "I am in check editText now", Toast.LENGTH_LONG).show();
                    UserRegisterFunction(F_Name_Holder,L_Name_Holder, EmailHolder, PasswordHolder);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(MainActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }


            }
        });



        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,UserLoginActivity.class);
               // Toast.makeText(MainActivity.this,"You are on onclick function",Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });


        /*HttpParse a=new HttpParse();
        String b=a.Hello("Hellllo");
        Toast.makeText(MainActivity.this,b+"this is value of b",Toast.LENGTH_SHORT).show();
    */
        //I did it just for playing


    }//End of oncreate()


   public void forlogin(View v){

       Intent intent = new Intent(getApplicationContext(),UserLoginActivity.class);
       // Toast.makeText(MainActivity.this,"You are on onclick function",Toast.LENGTH_SHORT).show();
       startActivity(intent);

   }

    public void CheckEditTextIsEmptyOrNot(){

        F_Name_Holder = First_Name.getText().toString();
        L_Name_Holder = Last_Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();


        if(TextUtils.isEmpty(F_Name_Holder) || TextUtils.isEmpty(L_Name_Holder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
//I have to remove the comment below its the actual code
            CheckEditText = false;
            //CheckEditText = true;
        }
        else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String F_Name, final String L_Name, final String email, final String password){

        class UserRegisterFunctionClass extends AsyncTask<String,String,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
               // Toast.makeText(MainActivity.this, "onPreExecute()", Toast.LENGTH_LONG).show();
               // progressDialog = ProgressDialog.show(MainActivity.this,"Loading Data","Hi whats up",true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

               // progressDialog.dismiss();
                //Toast.makeText(MainActivity.this,"You are on onPostExecute(String httpResponseMsg)",Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this,httpResponseMsg.toString()+"msg returned from server", Toast.LENGTH_LONG).show();
               // Toast.makeText(MainActivity.this,httpResponseMsg+"this is the message returned from server", Toast.LENGTH_LONG).show();
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                //Toast.makeText(MainActivity.this, values[0]+values[1], Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
//onProgressUpdate("Hello");
                //protected  Void publishProgress(Progress... values);
               // publishProgress("Sleeping now it is","For some time");
                hashMap.put("f_name",params[0]);

                hashMap.put("L_name",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("password",params[3]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);
                //finalResult+="It didnt worked";
                return finalResult;
            }
        }//End of User RegistrationClass

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(F_Name,L_Name,email,password);






    }//End of UserRegistration Function







}//end of last brace