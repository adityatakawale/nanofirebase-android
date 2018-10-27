package com.aditya.nanofirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button sendNote;
    EditText notice;
    FirebaseDatabase database;
    DatabaseReference ref;
    Index index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        sendNote = (Button) findViewById(R.id.sendNote);
        notice = (EditText) findViewById(R.id.noteBox);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Index");
        index = new Index();

    }
    private void getValues(){
    index.setNotice(notice.getText().toString());
    }

        public void sendNote(View view){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getValues();
                ref.child("Note").setValue(index);
                Toast.makeText(MainActivity.this,"Notice Inserted Succesfully...",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        }
}
