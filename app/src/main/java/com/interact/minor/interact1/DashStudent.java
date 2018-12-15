package com.interact.minor.interact1;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.interact.minor.interact1.API.AttendanceAPI;
import com.interact.minor.interact1.API.CircularAPI;
import com.interact.minor.interact1.API.EventAPI;
import com.interact.minor.interact1.API.MarksAPI;
import com.interact.minor.interact1.API.StudentAPI;
import com.interact.minor.interact1.Model.AttendanceModel;
import com.interact.minor.interact1.Model.CircularModel;
import com.interact.minor.interact1.Model.EventModel;
import com.interact.minor.interact1.Model.MarksModel;
import com.interact.minor.interact1.Model.StudentModel;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DashStudent extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    Dialog dialog , eventDialog , eventSpecificDialog , circulars;
    public static final String TAG ="YO!";
    String enroll_no = "";
    NavigationView navigationView;
    CardView cardSubjects, card1, cardMail, cardSem , cardYear , cardBranch , cardAttendance;
    TextView sName , sEnroll , sMail , sBranch , sYear , sSem ;
    Retrofit retrofit;
    StudentModel studentModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_student);
       View view = findViewById(R.id.mainDash);
       cardSubjects =  view.findViewById(R.id.subjects);
       card1 = view.findViewById(R.id.card1);
       cardAttendance = view.findViewById(R.id.attendance);
       eventSpecificDialog = new Dialog(this);
       cardMail = findViewById(R.id.cardMail);
       cardYear = findViewById(R.id.cardYear);
       cardBranch = findViewById(R.id.cardBranch);
       circulars = new Dialog(this);
       cardSem = findViewById(R.id.cardSem);
       sMail = cardMail.findViewById(R.id.tvStudentMail);
       sBranch = cardBranch.findViewById(R.id.tvStudentStream);
       sYear = cardYear.findViewById(R.id.tvStudentYear);
       sSem = cardSem.findViewById(R.id.tvStudentSem);
      // cardEnroll = view.findViewById(R.id.tvStudentEnroll);
       sName = card1.findViewById(R.id.tvStudentName);
       sEnroll = card1.findViewById(R.id.tvStudentEnroll);
        Log.i(TAG, "!!!!!!onCreate: " + cardSubjects);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView);
        /*Menu menu = navigationView.getMenu();
        MenuItem events = menu.getItem(R.id.events);*/
        dialog = new Dialog(this);
        eventDialog = new Dialog(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
                menuItem.setCheckable(true);
                if(menuItem.getItemId() == R.id.events)
                {
                EventAPI eventAPI = retrofit.create(EventAPI.class);
                eventAPI.getEvents().enqueue(new Callback<ArrayList<com.interact.minor.interact1.Model.EventModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<com.interact.minor.interact1.Model.EventModel>> call, Response<ArrayList<com.interact.minor.interact1.Model.EventModel>> response) {
                        Log.i(TAG, "!!!!!onResponse: " + response.body());
                        final ArrayList<EventModel> eventModelArrayList = response.body();
                        eventDialog.setContentView(R.layout.event_dialog);
                        TextView e1 = eventDialog.findViewById(R.id.event1);
                        TextView e2 = eventDialog.findViewById(R.id.event2);
                        TextView e3 = eventDialog.findViewById(R.id.event3);
                        e1.setText(eventModelArrayList.get(0).getTitle());
                        e2.setText(eventModelArrayList.get(1).getTitle());
                        e3.setText(eventModelArrayList.get(2).getTitle());
                        eventDialog.show();
                        eventSpecificDialog.setContentView(R.layout.specific_event_dialog);

                        e1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TextView ev1 = eventSpecificDialog.findViewById(R.id.eventHead);
                                TextView ev2 = eventSpecificDialog.findViewById(R.id.eventDate);
                                TextView ev3 = eventSpecificDialog.findViewById(R.id.eventDesc);
                                ev1.setText(eventModelArrayList.get(0).getTitle());
                                ev2.setText(eventModelArrayList.get(0).getDated());
                                ev3.setText(eventModelArrayList.get(0).getDescription());
                                eventSpecificDialog.show();
                            }
                        });
                        e2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TextView ev1 = eventSpecificDialog.findViewById(R.id.eventHead);
                                TextView ev2 = eventSpecificDialog.findViewById(R.id.eventDate);
                                TextView ev3 = eventSpecificDialog.findViewById(R.id.eventDesc);
                                ev1.setText(eventModelArrayList.get(1).getTitle());
                                ev2.setText(eventModelArrayList.get(1).getDated());
                                ev3.setText(eventModelArrayList.get(1).getDescription());
                                eventSpecificDialog.show();
                            }
                        });
                        e3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TextView ev1 = eventSpecificDialog.findViewById(R.id.eventHead);
                                TextView ev2 = eventSpecificDialog.findViewById(R.id.eventDate);
                                TextView ev3 = eventSpecificDialog.findViewById(R.id.eventDesc);
                                ev1.setText(eventModelArrayList.get(2).getTitle());
                                ev2.setText(eventModelArrayList.get(2).getDated());
                                ev3.setText(eventModelArrayList.get(2).getDescription());
                                eventSpecificDialog.show();
                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<ArrayList<com.interact.minor.interact1.Model.EventModel>> call, Throwable throwable) {

                    }
                });
                }
                if(menuItem.getItemId() == R.id.circulars)
                {
                    Log.i(TAG, "!!!!!!!!CIRCULARS");
                    circulars.setContentView(R.layout.circular_layout);
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://tushar1997.pythonanywhere.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    CircularAPI circularAPI = retrofit.create(CircularAPI.class);
                    circularAPI.getCircular().enqueue(new Callback<CircularModel>() {
                        @Override
                        public void onResponse(Call<CircularModel> call, Response<CircularModel> response) {
                            TextView date = circulars.findViewById(R.id.circDate);
                            TextView subject = circulars.findViewById(R.id.circSubject);
                            TextView content = circulars.findViewById(R.id.circText);
                            TextView issued = circulars.findViewById(R.id.circIssue);
                            Log.i(TAG, "!!!!!!onResponse: " + response.body());
                            date.setText(response.body().getDated());
                            subject.setText(response.body().getSubject());
                            content.setText(response.body().getText());
                            issued.setText(response.body().getIssuer_name());

                        }

                        @Override
                        public void onFailure(Call<CircularModel> call, Throwable throwable) {
                            Log.i(TAG, "!!!!!!!!onFailure: ");
                        }
                    });
                    circulars.show();
                }
                return true;
            }
        });

        enroll_no = getIntent().getStringExtra("enroll_no");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://tushar1997.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        DashStudent.AsyncStudent asyncStudent = new DashStudent.AsyncStudent(this,enroll_no);
        asyncStudent.execute(studentAPI);


    }


    class AsyncStudent extends AsyncTask<StudentAPI,Void,StudentModel>
    {
        Context context;
        String enroll_no;
        public AsyncStudent(Context context , String enroll_no)
        {
            this.context = context;
            this.enroll_no = enroll_no;
            Log.i(TAG, "!!!!AsyncStudent: " + enroll_no);
        }
        @Override
        protected StudentModel doInBackground(StudentAPI... studentAPIS) {
            StudentModel thisStudent = null;
            Response<StudentModel> response = null;
            try {
                response = studentAPIS[0].getByID(enroll_no).execute();
                thisStudent = response.body();
            } catch (IOException e) {
                Log.d(TAG, "!!!!onCreate: " + "EXCEPTION");
                e.printStackTrace();
            }
            Log.i(TAG, "!!!!onCreate: " + response.body());
            studentModel = response.body();
            return thisStudent;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "!!!!!!!onProgressUpdate: " + values);
        }

        @Override
        protected void onPostExecute(final StudentModel studentModel) {
            super.onPostExecute(studentModel);

            Log.i(TAG, "!!!!!!!!!onPostExecute: " + studentModel);
            sName.setText(studentModel.getName());
            sEnroll.setText(studentModel.getEnroll_no());
            sYear.setText(String.valueOf(studentModel.getYear()));
            sBranch.setText(studentModel.getStream());
            sSem.setText(studentModel.getSemester());
            sMail.setText(studentModel.getEmail());
            View view = navigationView.getHeaderView(0);
            TextView navText = view.findViewById(R.id.textHead);
            navText.setText(studentModel.getName());
            cardSubjects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPop(studentModel , 0);
                }
            });
            cardAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPop(studentModel , 1);
                }
            });

        }
    }

    public void showPop(final StudentModel studentModel , int flag) {
        dialog.setContentView(R.layout.dialog_layout);
        final TextView sub1 = dialog.findViewById(R.id.subject1);
        final TextView sub2 = dialog.findViewById(R.id.subject2);
        final TextView sub3 = dialog.findViewById(R.id.subject3);
        final TextView sub4 = dialog.findViewById(R.id.subject4);
        final TextView sub5 = dialog.findViewById(R.id.subject5);
        if(flag == 0)
        {
            MarksAPI marksAPI = retrofit.create(MarksAPI.class);
            marksAPI.getMarksById(enroll_no).enqueue(new Callback<MarksModel>() {
                @Override
                public void onResponse(Call<MarksModel> call, Response<MarksModel> response) {
                    MarksModel marksModel = response.body();
                    sub1.setText(String.valueOf(marksModel.getSubject1()) + "  " + marksModel.getSubject1_int());
                    sub2.setText(String.valueOf(marksModel.getSubject2())  + "  " + marksModel.getSubject2_int());
                    sub3.setText(String.valueOf(marksModel.getSubject3())  + "  " + marksModel.getSubject3_int());
                    sub4.setText(String.valueOf(marksModel.getSubject4())  + "  " + marksModel.getSubject4_int());
                    sub5.setText(String.valueOf(marksModel.getSubject5()) + "  " + marksModel.getSubject5_int());
                }

                @Override
                public void onFailure(Call<MarksModel> call, Throwable throwable) {

                }
            });
        }else
        {
            /*MarksAPI marksAPI = retrofit.create(MarksAPI.class);
            marksAPI.getMarksById(enroll_no).enqueue(new Callback<MarksModel>() {
                @Override
                public void onResponse(Call<MarksModel> call, Response<MarksModel> response) {
                    MarksModel marksModel = response.body();
                    sub1.setText(String.valueOf(marksModel.getSubject1()) + "  " + marksModel.getSubject1_int());
                    sub2.setText(String.valueOf(marksModel.getSubject2())  + "  " + marksModel.getSubject2_int());
                    sub3.setText(String.valueOf(marksModel.getSubject3())  + "  " + marksModel.getSubject3_int());
                    sub4.setText(String.valueOf(marksModel.getSubject4())  + "  " + marksModel.getSubject4_int());
                    sub5.setText(String.valueOf(marksModel.getSubject5()) + "  " + marksModel.getSubject5_int());
                }

                @Override
                public void onFailure(Call<MarksModel> call, Throwable throwable) {

                }
            });*/
            AttendanceAPI attendanceAPI = retrofit.create(AttendanceAPI.class);
            attendanceAPI.getAttById(enroll_no).enqueue(new Callback<AttendanceModel>() {
                @Override
                public void onResponse(Call<AttendanceModel> call, Response<AttendanceModel> response) {
                    AttendanceModel thisModel = response.body();
                    sub1.setText(String.valueOf(thisModel.getSubject1() + "  " + thisModel.getSubject1_att()));
                    sub2.setText(String.valueOf(thisModel.getSubject2() + "  " + thisModel.getSubject2_att()));
                    sub3.setText(String.valueOf(thisModel.getSubject3() + "  " + thisModel.getSubject3_att()));
                    sub4.setText(String.valueOf(thisModel.getSubject4() + "  " + thisModel.getSubject4_att()));
                    sub5.setText(String.valueOf(thisModel.getSubject5() + "  " + thisModel.getSubject5_att()));
                }

                @Override
                public void onFailure(Call<AttendanceModel> call, Throwable throwable) {

                }
            });
        }



        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
              return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
