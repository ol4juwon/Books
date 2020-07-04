package com.ol4juwon.books;

import androidx.appcompat.app.AppCompatActivity;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
//        TextView tvResult = (TextView) findViewById( R.id.tvResponse);
        try {
            URL bookUrl = ApiUtil.buildURl("fishing");
//            String jsonResult = ApiUtil.getJson(bookUrl);
            new BooksQueryTask().execute(bookUrl);
//            tvResult.setText(jsonResult);

        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }
    }
    public class BooksQueryTask extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String result = null;
            try{
                result = ApiUtil.getJson(searchUrl);
            }catch(IOException e){
                Log.e("Error doInBackground: ",e.getMessage());

            }
            return result;
        }

        protected void onPostExecute(String result){
            TextView tvResult = (TextView) findViewById(R.id.tvResponse);
            tvResult.setText(result);
           // tvResult.setVisibility(View.VISIBLE);



//            super.onPostExecute(s);
        }
    }
}