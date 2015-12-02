package db.kuet.musta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

	Button load, pre;
	TextView uUserName, uPassWord;
	final static String DEFAULT = "N/A";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		initialization();
	}

	public void initialization() {
		load = (Button) findViewById(R.id.load);
		pre = (Button) findViewById(R.id.pre);
		uUserName = (TextView) findViewById(R.id.uUserName);
		uPassWord = (TextView) findViewById(R.id.uPassWord);
		load.setOnClickListener(this);
		pre.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == load){
			SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
			String name = sharedPreferences.getString("userName", DEFAULT);
			String pass = sharedPreferences.getString("passWord", DEFAULT);
			if (name.equals(DEFAULT) || pass.equals(DEFAULT)){
				Toast.makeText(UserActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(UserActivity.this, "Data Loaded!", Toast.LENGTH_SHORT).show();
				uUserName.setText(name);
				uPassWord.setText(pass);
			}
		}
		else if (v == pre){
			Toast.makeText(UserActivity.this, "Previous", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, MainActivity.class));
			finish();
		}
	}
}
