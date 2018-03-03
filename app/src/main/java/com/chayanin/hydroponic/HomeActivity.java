package com.chayanin.hydroponic;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chayanin.hydroponic.model.ControllerModel;
import com.chayanin.hydroponic.model.RelayModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    ImageView relay1, relay2, relay3, relay4, switch_main;
    TextView status_relay1, status_relay2, status_relay3, status_relay4;
    TextView namerelay1, namerelay2, namerelay3, namerelay4;
    PieView sensor1, sensor2;

    int status1 = 0;
    int status2 = 0;
    int status3 = 0;
    int status4 = 0;
    int run = 0;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProgressDialog(LOAD);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("controller").child("1123");

        bindView();

        getValue();

    }

    private void bindView() {
        switch_main = findViewById(R.id.switch_main);
        relay1 = findViewById(R.id.relay1);
        relay2 = findViewById(R.id.relay2);
        relay3 = findViewById(R.id.relay3);
        relay4 = findViewById(R.id.relay4);
        namerelay1 = findViewById(R.id.namerelay1);
        namerelay2 = findViewById(R.id.namerelay2);
        namerelay3 = findViewById(R.id.namerelay3);
        namerelay4 = findViewById(R.id.namerelay4);
        status_relay1 = findViewById(R.id.status_relay1);
        status_relay2 = findViewById(R.id.status_relay2);
        status_relay3 = findViewById(R.id.status_relay3);
        status_relay4 = findViewById(R.id.status_relay4);
        sensor1 = findViewById(R.id.pieView1);
        sensor2 = findViewById(R.id.pieView2);

        relay1.setOnClickListener(this);
        relay2.setOnClickListener(this);
        relay3.setOnClickListener(this);
        relay4.setOnClickListener(this);
        switch_main.setOnClickListener(this);

        namerelay1.setText(getString(R.string.realy1));
        namerelay2.setText(getString(R.string.realy2));
        namerelay3.setText(getString(R.string.realy3));
        namerelay4.setText(getString(R.string.realy4));

        status_relay1.setText("(ปิดอยู่)");
        status_relay2.setText("(ปิดอยู่)");
        status_relay3.setText("(ปิดอยู่)");
        status_relay4.setText("(ปิดอยู่)");

        PieAngleAnimation animation1 = new PieAngleAnimation(sensor1);
        PieAngleAnimation animation2 = new PieAngleAnimation(sensor2);
        animation1.setDuration(3000);
        animation2.setDuration(3000);

        sensor1.startAnimation(animation1);
        sensor2.startAnimation(animation2);

        sensor1.setMaxPercentage(14);
        sensor2.setMaxPercentage(100);
    }

    private void getValue() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setValue(dataSnapshot.getValue(ControllerModel.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setValue(ControllerModel model) {

        if (model.getSystem().getRun() == 0) {
            run = 0;
            switch_main.setImageDrawable(getDrawable(R.drawable.off));
        } else if (model.getSystem().getRun() == 1) {
            run = 1;
            switch_main.setImageDrawable(getDrawable(R.drawable.on));
        }

        if (model.getRelay().getPum1() == 0) {
            status_relay1.setText("(ปิดอยู่)");
            status1 = 0;
            relay1.setImageDrawable(getDrawable(R.drawable.switch_off));
        } else if (model.getRelay().getPum1() == 1) {
            status_relay1.setText("(เปิดอยู่)");
            status1 = 1;
            relay1.setImageDrawable(getDrawable(R.drawable.switch_on));
        }

        if (model.getRelay().getPum2() == 0) {
            status_relay4.setText("(ปิดอยู่)");
            status4 = 0;
            relay4.setImageDrawable(getDrawable(R.drawable.switch_off));
        } else if (model.getRelay().getPum2() == 1) {
            status_relay4.setText("(เปิดอยู่)");
            status4 = 1;
            relay4.setImageDrawable(getDrawable(R.drawable.switch_on));
        }

        if (model.getRelay().getSo1() == 0) {
            status_relay2.setText("(ปิดอยู่)");
            status2 = 0;
            relay2.setImageDrawable(getDrawable(R.drawable.switch_off));
        } else if (model.getRelay().getSo1() == 1) {
            status_relay2.setText("(เปิดอยู่)");
            status2 = 1;
            relay2.setImageDrawable(getDrawable(R.drawable.switch_on));
        }

        if (model.getRelay().getSo2() == 0) {
            status_relay3.setText("(ปิดอยู่)");
            status3 = 0;
            relay3.setImageDrawable(getDrawable(R.drawable.switch_off));
        } else if (model.getRelay().getSo2() == 1) {
            status_relay3.setText("(เปิดอยู่)");
            status3 = 1;
            relay3.setImageDrawable(getDrawable(R.drawable.switch_on));
        }

        if (model.getSensor().getPh() > 0) {
            sensor1.setPercentage((float) model.getSensor().getPh());
            sensor1.setInnerText(model.getSensor().getPh() + " pH");
        } else if (model.getSensor().getPh() == 0){
            sensor1.setPercentage((float) 0.1);
            sensor1.setInnerText(model.getSensor().getPh() + " pH");
        }

        if (model.getSensor().getTempleWater() > 0) {
            sensor2.setPercentage((float) model.getSensor().getTempleWater());
            sensor2.setInnerText(model.getSensor().getTempleWater() + "°c");
        } else if (model.getSensor().getTempleWater() == 0){
            sensor2.setPercentage((float) 0.1);
            sensor2.setInnerText(model.getSensor().getTempleWater() + "°c");
        }

        hideProgressDialog();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relay1:
                dialogSwitch(getString(R.string.realy1), status1, "pum1");
                break;
            case R.id.relay2:
                dialogSwitch(getString(R.string.realy2), status2, "so1");
                break;
            case R.id.relay3:
                dialogSwitch(getString(R.string.realy3), status3, "so2");
                break;
            case R.id.relay4:
                dialogSwitch(getString(R.string.realy4), status4, "pum2");
                break;
            case R.id.switch_main:
                dialogSwitch("ระบบ "+getString(R.string.app_name), run, "run");
                break;
        }
    }

    private void dialogSwitch(String name, int status, final String id) {
        String detail = "";
        if (status == 0) {
            status += 1;
            detail = name + " ปิดใช้งานอยู่ ต้องการเปิดหรือไม่ ?";
        } else if (status == 1) {
            status -= 1;
            detail = name + " เปิดใช้งานอยู่ ต้องการปิดหรือไม่ ?";
        }

        final int finalStatus = status;

        new AlertDialog.Builder(this, R.style.AppTheme_Dark_Dialog)
                .setTitle("แจ้งเตือน")
                .setMessage(detail)
                .setNegativeButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showProgressDialog(VERIFY);
                        dialogInterface.dismiss();
                        changStatusRelay(finalStatus, id);
                    }
                }).setPositiveButton("ยกเลิก", null)
                .setCancelable(false)
                .show();
    }

    private void changStatusRelay(int status, String id) {
        if (id.equals("run")) {
            myRef.child("system").child(id).setValue(status);
            hideProgressDialog();
        } else {
            myRef.child("relay").child(id).setValue(status);
            hideProgressDialog();
        }
    }
}
