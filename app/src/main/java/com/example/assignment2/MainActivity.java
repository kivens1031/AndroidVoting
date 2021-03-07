package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    TextView candidate1;
    TextView candidate2;
    TextView candidate3;
    Button voteButton;
    Candidate[] candidates = new Candidate[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidates[0] = new Candidate("Candidate 1");
        candidates[1] = new Candidate("Candidate 2");
        candidates[2] = new Candidate("Candidate 3");

        candidate1 = findViewById(R.id.voter_name);
        candidate2 = findViewById(R.id.candidate2);
        candidate3 = findViewById(R.id.candidate3);
        voteButton = findViewById(R.id.btn_vote);

        candidate1.setText(candidates[0].getVoteNum() + "");
        candidate2.setText(candidates[1].getVoteNum() + "");
        candidate3.setText(candidates[2].getVoteNum() + "");



        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                votePage(v);
            }
        });
    }

    public void votePage(View view){
        Intent intent =new Intent(this,VoteActivity.class);
        intent.putExtra("candidate1", (new Gson()).toJson(candidates[0]));
        intent.putExtra("candidate2", (new Gson()).toJson(candidates[1]));
        intent.putExtra("candidate3", (new Gson()).toJson(candidates[2]));
        startActivity(intent);
    }

    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();
        try{
            String candid1 = intent.getExtras().getString("candidate1");
            String candid2 = intent.getExtras().getString("candidate2");
            String candid3 = intent.getExtras().getString("candidate3");
            Gson gson = new Gson();
            candidates[0] = gson.fromJson(candid1, Candidate.class);
            candidates[1] = gson.fromJson(candid2, Candidate.class);
            candidates[2] = gson.fromJson(candid3, Candidate.class);

            candidate1 = findViewById(R.id.voter_name);
            candidate2 = findViewById(R.id.candidate2);
            candidate3 = findViewById(R.id.candidate3);
            candidate1.setText(candidates[0].getVoteNum() + "");
            candidate2.setText(candidates[1].getVoteNum() + "");
            candidate3.setText(candidates[2].getVoteNum() + "");
        }catch(Exception e){
            Log.d("Main activity2 onResume", "noIntent: " + e);
        }
        Log.d("Main Activity", "onResume:2 ");
    }

}