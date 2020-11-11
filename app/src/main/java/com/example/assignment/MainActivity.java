package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerUserInfo;
    Context mContext;
    private EditText editSearch;
    private ProgressDialog progressDialog;
    public static String TAG = "MainActivity";
    private ResponseModel responseModel;
    ArrayList<ResponseModel.Search> searchResultList;
    MovieListAdapter movieListAdapter;
    public static String APIKEY = "6c160ea3";
    String searchkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.text_solace_assignment));
        progressDialog = new ProgressDialog(mContext);
        recyclerUserInfo = (RecyclerView)findViewById(R.id.recyclerUserInfo);
        editSearch = (EditText)findViewById(R.id.etSearch);

        progressDialog.setTitle("Loading.. Please Wait..!!");
        progressDialog.show();
        searchkey = "batman";
        loadMovies(searchkey);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchkey = editSearch.getText().toString();
                loadMovies(searchkey);
            }
        });



    }

    private void loadMovies(String searchkey) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> responseModelCall = apiInterface.getData("?s="+searchkey+"&apikey="+APIKEY+"");
        responseModelCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    responseModel = response.body();
                    if(responseModel.getResponse().equalsIgnoreCase("True")){
                        searchResultList = new ArrayList<>();
                        for (int i = 0; i < responseModel.getSearch().size(); i++) {
                            searchResultList.add(responseModel.getSearch().get(i));
                        }
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
                        recyclerUserInfo.setLayoutManager(mLayoutManager);
                        movieListAdapter= new MovieListAdapter(mContext,searchResultList);
                        recyclerUserInfo.setAdapter(movieListAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
