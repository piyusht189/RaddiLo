package com.example.milkiminz.raddilo;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class UpdateShop extends AppCompatActivity {

    EditText nm,ph,add;
    String em;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateshop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nm=(EditText)findViewById(R.id.name);
        add=(EditText)findViewById(R.id.address);
        ph=(EditText)findViewById(R.id.phone);
        Intent i=getIntent();
        nm.setText(i.getStringExtra("name"));
        add.setText(i.getStringExtra("address"));
        ph.setText(i.getStringExtra("phone"));
        em=i.getStringExtra("email");
        requestQueue= Volley.newRequestQueue(UpdateShop.this);
    }
    public void update1(View view){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.updateshop), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals(getResources().getString(R.string.success))) {
                    Toast.makeText(UpdateShop.this, getResources().getString(R.string.success)+" Updated", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(UpdateShop.this,ProfileShop.class));
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(UpdateShop.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Creating parameters
                Map<String,String> params = new Hashtable<>();

                //Adding parameters

                params.put("name",nm.getText().toString());
                params.put("address",add.getText().toString());
                params.put("phone",ph.getText().toString());
                params.put("email",em);


                return params;
            }
        };
        requestQueue.add(stringRequest);


    }

}
