package com.example.registration;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.makeText;

public class HttpParse {

    String FinalHttpData = "";
    String Result ;
    BufferedWriter bufferedWriter ;
    OutputStream outputStream ;
    BufferedReader bufferedReader ;
    StringBuilder stringBuilder = new StringBuilder();
    URL url;



  /*  public String Hello(String s){

        //Toast.makeText(HttpParse.this,"I am in try block of post request",Toast.LENGTH_SHORT).show();

   return s;
    };  */


    public String postRequest(HashMap<String, String> Data, String HttpUrlHolder) {
       // Toast.makeText(,"You are on HttpParse",Toast.LENGTH_SHORT).show();
        //Log.d("HII","What jjjj");

        try {
            url = new URL(HttpUrlHolder);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

           // httpURLConnection.setReadTimeout(14000);

           // httpURLConnection.setConnectTimeout(14000);

            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);

            httpURLConnection.setDoOutput(true);

            //FinalHttpData+= "Hiiiii";
            outputStream = httpURLConnection.getOutputStream();

            bufferedWriter = new BufferedWriter(

                    new OutputStreamWriter(outputStream, "UTF-8"));


            //FinalHttpData+= "Hiiiii";
            bufferedWriter.write(FinalDataParse(Data));
           // FinalHttpData+= "vvvvv";

            bufferedWriter.flush();

            bufferedWriter.close();

            outputStream.close();


            //below if statement I have written just for experiment

           // if (5==5) {


            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                //FinalHttpData+= "Hii5==5";

                bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                httpURLConnection.getInputStream()
                        )

                );

                //below code is written by me
             /*   String line="";
                while (line!=null){
                    line=bufferedReader.readLine();
                    FinalHttpData+=line;
                }*/



              //  FinalHttpData+= "after readeri";
             /*   BufferedReader reader = new BufferedReader(new
                        InputStreamReader(httpURLConnection.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }


                //below code is copied from mysql so remove comment
               // return sb.toString();

                FinalHttpData = sb.toString();   */










         //Below code is original Ill have to remove the comment
                FinalHttpData = bufferedReader.readLine();
                //FinalHttpData+="I am in if block";






            }//End of if
            else {
               // FinalHttpData = "Something Went Wrong";

                FinalHttpData = "Something Went Wrong";
            }

            //I am addig return statement for my use
            return FinalHttpData;
        }//End of try

        catch (Exception e) {
            e.printStackTrace();
            String t=e.toString();
            FinalHttpData+="jjjjj"+t;
        }
        //FinalHttpData+="kkk";
       // FinalHttpData+="SOOppp";
        //FinalHttpData+= Data;

        return FinalHttpData;
    }//End of postRequest



    public String FinalDataParse(HashMap<String,String> hashMap2) throws UnsupportedEncodingException {

        for(Map.Entry<String,String> map_entry : hashMap2.entrySet()){

            stringBuilder.append("&");

            stringBuilder.append(URLEncoder.encode(map_entry.getKey(), "UTF-8"));

            stringBuilder.append("=");

            stringBuilder.append(URLEncoder.encode(map_entry.getValue(), "UTF-8"));

        }//End of for loop

        Result = stringBuilder.toString();


        FinalHttpData+="JLKJKLKJ";//This line I written for exp

        return Result ;
    }//End of FinalDataParse







}//End of HttpParse
