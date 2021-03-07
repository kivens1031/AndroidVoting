package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class VoteActivity extends AppCompatActivity {

    Candidate[] candidates = new Candidate[3];
    Button voteButton;
    Spinner sp;
    TextView name;
    TextView id;
    Button accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        sp = findViewById(R.id.candidate);
        voteButton = findViewById(R.id.btn_vote);
        name = findViewById(R.id.voter_name);
        id = findViewById(R.id.voter_id);
        accept = findViewById(R.id.btn_action);

        Intent intent = getIntent(); // I recieve the intent I created on previous page
        String candid1 = intent.getExtras().getString("candidate1");
        String candid2 = intent.getExtras().getString("candidate2");
        String candid3 = intent.getExtras().getString("candidate3");
        Gson gson = new Gson();
        candidates[0] = gson.fromJson(candid1, Candidate.class);
        candidates[1] = gson.fromJson(candid2, Candidate.class);
        candidates[2] = gson.fromJson(candid3, Candidate.class);

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> errorMessages = new ArrayList<String>();

                if(accept.getText().toString().equalsIgnoreCase("refuse") ){
                    errorMessages.add("--Please toggle the button to Accept if you accept that you are about to vote the candidate");
                }else{
                    if(name.getText().toString().equalsIgnoreCase("")){
                        errorMessages.add("--Please enter your name");
                    }
                    if(id.getText().toString().equalsIgnoreCase("")){
                        errorMessages.add("--Please enter your id");
                    }

                    for(int i = 0; i<3 ; i++){
                        List<Voter> voters = candidates[i].getVoters();
                        for(Voter voter: voters){
                            if(voter.getId().equalsIgnoreCase(id.getText().toString())){
                                errorMessages.add("--You have already voted for " + candidates[i].getName());
                            }
                        }
                    }
                }


                if(errorMessages.size() != 0){
                    String errorMessage = "";
                    for(String s: errorMessages){
                        errorMessage += (s + "\n");
                    }
                    Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_SHORT).show();

                }
                else{
                Log.d("SELECTED ITEM", sp.getSelectedItem().toString());
                if(sp.getSelectedItem().toString().equalsIgnoreCase("Candidate 1")){
                    candidates[0].addVoter(name.getText().toString(), id.getText().toString());
                }
                if(sp.getSelectedItem().toString().equalsIgnoreCase("Candidate 2")){
                    candidates[1].addVoter(name.getText().toString(), id.getText().toString());
                }
                if(sp.getSelectedItem().toString().equalsIgnoreCase("Candidate 3")){
                    candidates[2].addVoter(name.getText().toString(), id.getText().toString());
                }

                Log.d("Before Voting", "" +new Gson().toJson(candidates));
                votePage1(v);
                }
            }
        });
    }

    public void votePage1(View view){
        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("candidate1", (new Gson()).toJson(candidates[0]));
        intent.putExtra("candidate2", (new Gson()).toJson(candidates[1]));
        intent.putExtra("candidate3", (new Gson()).toJson(candidates[2]));
        startActivity(intent);
    }
}
