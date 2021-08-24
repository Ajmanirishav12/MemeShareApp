package com.example.memeshareapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public  class MainActivity extends AppCompatActivity
        {
          ProgressBar prog;
            String curimg;
protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);     // Calling the Superclass onCreate Method.
        setContentView(R.layout.activity_main);
        //This function is used to set the Content on the Screen.
             prog = findViewById(R.id.progbar);
             prog.setVisibility(View.VISIBLE);
             curimg = null;
             getSupportActionBar().setDisplayShowHomeEnabled(true);
            sendrequest();
        }
        public void sendrequest()
        {
            ImageView imageView = findViewById(R.id.memeImageView);
// Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://meme-api.herokuapp.com/gimme";           //Api Used in this app.
// Request a string response from the provided URL.
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            // Display the first 500 characters of the response string.
                            try {
                                curimg = response.getString( "url");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            curimg = curimg.toString();
                            Glide.with(getApplicationContext()).load(curimg).into(imageView);
                            prog.setVisibility(View.INVISIBLE);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                }
            });
// Add the request to the RequestQueue.
            queue.add(jsonObjectRequest);
        }
            public void shareMeme(View view)
            {
             Intent intent = new Intent(Intent.ACTION_SEND);
             intent.setType(getString(R.string.typr));
             intent.putExtra(intent.EXTRA_TEXT, "Hey Check out this Cool Meme For You  " + curimg);
             startActivity(intent.createChooser(intent, "Share This Meme Using....." ));
            }
            public void nextMeme(View view)
            {
                prog.setVisibility(View.VISIBLE);
                sendrequest();

            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu)
            {
                menu.add("LOGOUT");
                return super.onCreateOptionsMenu(menu);
            }

            @Override
            public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                if (item.getTitle().equals("LOGOUT")) ;
                {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(MainActivity.this, Login_Activity.class);
                    startActivity(intent);
                    finish();
                }
                return super.onOptionsItemSelected(item);
            }
        }
