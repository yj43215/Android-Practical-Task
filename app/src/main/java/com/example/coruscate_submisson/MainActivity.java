package com.example.coruscate_submisson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coruscate_submisson.Model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;



    // Server Http URL
    String HTTP_URL = "https://jsonplaceholder.typicode.com/users";

    // String to hold complete JSON response object.
    String FinalJSonObject ;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign ID’S
        listView =  findViewById(R.id.listView1);

        progressBar = findViewById(R.id.ProgressBar1);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserModel item = (UserModel) listView.getItemAtPosition(i);

               // Toast.makeText(MainActivity.this, "Name"+item.getName()+" NO"+i, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,ProfilePage.class);
                intent.putExtra("name",item.getName());
                intent.putExtra("city",item.getAddress());
                intent.putExtra("email",item.getEmail());
                intent.putExtra("phone",item.getPhone());
                intent.putExtra("website",item.getWebsite());
                intent.putExtra("company",item.getCompany());
                startActivity(intent);
            }
        });




//Showing progress bar just after button click.
        progressBar.setVisibility(View.VISIBLE);

        // Creating StringRequest and set the JSON server URL in here.
        StringRequest stringRequest = new StringRequest(HTTP_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // After done Loading store JSON response in FinalJSonObject string variable.
                        FinalJSonObject = response ;

                        // Calling method to parse JSON object.
                        new ParseJSonDataClass(MainActivity.this).execute();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // Showing error message if something goes wrong.
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

        // Creating String Request Object.
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        // Passing String request into RequestQueue.
        requestQueue.add(stringRequest);

    }



    // Creating method to parse JSON object.
    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {

        public Context context;

        // Creating List of Subject class.
        List<UserModel> CustomSubjectNamesList;

        public ParseJSonDataClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {

                // Checking whether FinalJSonObject is not equals to null.
                if (FinalJSonObject != null) {

                    // Creating and setting up JSON array as null.
                    JSONArray jsonArray = null;
                    try {

                        // Adding JSON response object into JSON array.
                        jsonArray = new JSONArray(FinalJSonObject);

                        // Creating JSON Object.
                        JSONObject jsonObject;

                        // Creating Subject class object.
                        UserModel subject;

                        // Defining CustomSubjectNamesList AS Array List.
                        CustomSubjectNamesList = new ArrayList<UserModel>();

                        for (int i = 0; i < jsonArray.length(); i++) {

                            subject = new UserModel();

                            jsonObject = jsonArray.getJSONObject(i);


                            //Storing ID into subject list.
                            subject.setName(jsonObject.getString("name"));
                            subject.setId(jsonObject.getString("id"));
                            subject.setEmail(jsonObject.getString("email"));
                            JSONObject jsonObjectcompany=jsonObject.getJSONObject("company");
                            JSONObject jsonObjectadd=jsonObject.getJSONObject("address");
                            subject.setAddress(jsonObjectadd.getString("street"));
                           subject.setWebsite(jsonObject.getString("website"));
                            subject.setCompany(jsonObjectcompany.getString("name"));
                            subject.setPhone(jsonObject.getString("phone"));
                            //Storing Subject name in subject list.

                            // Adding subject list object into CustomSubjectNamesList.
                            CustomSubjectNamesList.add(subject);
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            // After all done loading set complete CustomSubjectNamesList with application context to ListView adapter.
            ListViewAdapter adapter = new ListViewAdapter(CustomSubjectNamesList, context);

            // Setting up all data into ListView.
            listView.setAdapter(adapter);

            // Hiding progress bar after all JSON loading done.
            progressBar.setVisibility(View.GONE);

        }



}
}







