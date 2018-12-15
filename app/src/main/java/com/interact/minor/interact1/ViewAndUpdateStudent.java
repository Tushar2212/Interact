package com.interact.minor.interact1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.interact.minor.interact1.API.StudentAPI;
import com.interact.minor.interact1.API.SubjectsAPI;
import com.interact.minor.interact1.Model.StudentModel;
import com.interact.minor.interact1.Model.SubjectModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.interact.minor.interact1.TeacherDash.subList;

public class ViewAndUpdateStudent extends AppCompatActivity {
    public  ViewAndUpdateStudent() {}
    public ViewAndUpdateStudent(ArrayList<SubjectModel> subjectModelList)
    {
        this.subjectModelList = subjectModelList;
    }
    private static final String TAG ="GG" ;
    Button viewStudent , upStudent , upAtt1 , upAtt2 , upMarks1 , upMarks2;
    EditText enrollment;
    View lienarL;
    TextView sname , senroll , sub1name , sub2name ;
    EditText att1 ,att2 , marks1 , marks2;
    StudentModel model = null;
    ArrayList<SubjectModel> subjectModelList;
    Retrofit retrofit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "!!!!!onCreate: " + subjectModelList);
        setContentView(R.layout.activity_view_and_update_student);
        viewStudent = findViewById(R.id.btnViewStudent);
        upStudent = findViewById(R.id.btnUpdate);
        enrollment = findViewById(R.id.enterEnroll);
        lienarL = findViewById(R.id.linaerLayput);
        lienarL.setVisibility(View.INVISIBLE);
        sname = findViewById(R.id.tvName);
        senroll = findViewById(R.id.tvEnroll);
        sub1name = findViewById(R.id.sub1Name);
        sub2name = findViewById(R.id.sub2Name);
        att1 = findViewById(R.id.att1);
        att2 = findViewById(R.id.att2);
        marks1 = findViewById(R.id.marks1);
        marks2 = findViewById(R.id.marks2);
        upAtt1 = findViewById(R.id.updateAttendance1);
        upAtt2 = findViewById(R.id.updateAttendance2);
        upMarks1 = findViewById(R.id.updateMarks1);
        upMarks2 = findViewById(R.id.updateMarks2);
        final StudentModel stuModel = getStudent(enrollment.getText().toString());
        String teacherId = getIntent().getStringExtra("teacherId");
        Log.i(TAG, "!!!!!!onCreate: " + teacherId);
        Log.i(TAG, "!!!!!!onCreate: " + subjectModelList);
        ArrayList<String> st = new ArrayList<>();
        for(SubjectModel s : subList)
        {
            if(s.getUser() == Integer.valueOf(teacherId))
                st.add(s.getSubject());
        }
        for(int i = 0 ; i < st.size() ; i++)
        {
            switch (i)
            {
                case 0 :
                    sub1name.setText(st.get(0)); break;
                case 1 :
                    sub2name.setText(st.get(1));

            }
        }
        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAndUpdateStudent.this , DashStudent.class);
                intent.putExtra("enroll_no" , enrollment.getText().toString());
                startActivity(intent);
            }
        });
        upStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lienarL.setVisibility(View.VISIBLE);
                sname.setText(stuModel.getName());
                senroll.setText(stuModel.getEnroll_no());

            }
        });
    }



    public StudentModel getStudent(String s) {

         retrofit = new Retrofit.Builder()
                                .baseUrl("http://tushar1997.pythonanywhere.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        StudentAPI thisAPI = retrofit.create(StudentAPI.class);
        thisAPI.getByID(s).enqueue(new Callback<StudentModel>() {
            @Override
            public void onResponse(Call<StudentModel> call, Response<StudentModel> response) {
                model = response.body();
            }

            @Override
            public void onFailure(Call<StudentModel> call, Throwable throwable) {

            }
        });
        return  model;
    }
}
