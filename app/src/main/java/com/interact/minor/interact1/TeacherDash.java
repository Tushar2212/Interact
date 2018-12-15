package com.interact.minor.interact1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.interact.minor.interact1.API.SubjectsAPI;
import com.interact.minor.interact1.API.TeacherLogin;
import com.interact.minor.interact1.Model.SubjectModel;
import com.interact.minor.interact1.Model.TeacherModel;
import com.interact.minor.interact1.Model.TeacherResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeacherDash extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG="LOL";
    Button viewStudent , eventCreate;
    TextView userId;
    Retrofit retrofit;
    TeacherModel teacherModel ;
    static ArrayList<SubjectModel> subList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        viewStudent = findViewById(R.id.viewStu);
        eventCreate = findViewById(R.id.createEvent);
        userId = findViewById(R.id.userId);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://tushar1997.pythonanywhere.com/api/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        String teacherUserName = getIntent().getStringExtra("username");
        userId.setText(teacherUserName);
        TeacherLogin teacherLogin = retrofit.create(TeacherLogin.class);
        teacherLogin.getTeach(new TeacherResponse(teacherUserName , "" , teacherUserName)).enqueue(new Callback<TeacherModel>() {
            @Override
            public void onResponse(Call<TeacherModel> call, Response<TeacherModel> response) {
                Log.i(TAG, "!!!!!onResponse: " + response.body());
                teacherModel = response.body();
                final int id = teacherModel.getId();
                final TextView sub1 = findViewById(R.id.sub1);
                final TextView sub2 = findViewById(R.id.sub2);
                SubjectsAPI subjectsAPI = retrofit.create(SubjectsAPI.class);
                subjectsAPI.getSubjects(id).enqueue(new Callback<ArrayList<SubjectModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SubjectModel>> call, final Response<ArrayList<SubjectModel>> response) {
                        subList = response.body();
                        new ViewAndUpdateStudent(response.body());
                        ArrayList<String> st = new ArrayList<>();
                        for(SubjectModel s : subList)
                        {
                            if(s.getUser() == id)
                                st.add(s.getSubject());
                        }
                        for(int i = 0 ; i < st.size() ; i++)
                        {
                            switch (i)
                            {
                                case 0 :
                                    sub1.setText(st.get(0)); break;
                                case 1 :
                                    sub2.setText(st.get(1));

                            }
                        }
                        viewStudent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(TeacherDash.this , ViewAndUpdateStudent.class);

                                intent.putExtra("teacherId" , String.valueOf(id));
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SubjectModel>> call, Throwable throwable) {

                    }
                });
            }

            @Override
            public void onFailure(Call<TeacherModel> call, Throwable throwable) {

            }
        });

      //  Log.i(TAG, "!!!!!!!!onCreate: " + id);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teacher_dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.teacherEvents) {
            // Handle the camera action
        } else if (id == R.id.singOut) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
