package db.kuet.musta;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	Button signUp, goTo, tempGo;
	EditText userName, passWord;
	String user1, pass1, userFromSP, passFromSP;
	final static String DEFAULT = "N/A";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialization();
	}

	public void initialization() {
		signUp = (Button) findViewById(R.id.signUp);
		goTo = (Button) findViewById(R.id.goTo);
		userName = (EditText) findViewById(R.id.userName);
		passWord = (EditText) findViewById(R.id.passWord);
		signUp.setOnClickListener(this);
		goTo.setOnClickListener(this);
		tempGo = (Button) findViewById(R.id.tempGo);
		tempGo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == signUp){
			SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			user1 = userName.getText().toString();
			pass1 = passWord.getText().toString();
			editor.putString("userName", user1);
			editor.putString("passWord", pass1);
			if (user1.isEmpty() || pass1.isEmpty()){
				Toast.makeText(MainActivity.this, "No Data to save!!", Toast.LENGTH_SHORT).show();
			}else {
				editor.commit();
				Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
			}
		}
		else if (v == goTo){
			SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
			userFromSP = sharedPreferences.getString("userName", DEFAULT);
			passFromSP = sharedPreferences.getString("passWord", DEFAULT);
			Toast.makeText(MainActivity.this, "Goto", Toast.LENGTH_SHORT).show();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Login to go");
			LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.VERTICAL);
			final EditText loginUserName = new EditText(this);
			final EditText loginPassWord = new EditText(this);
			loginUserName.setHint("Username");
			loginPassWord.setHint("Password");
			layout.addView(loginUserName);
			layout.addView(loginPassWord);
			builder.setView(layout);
			builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					final String loginUser = loginUserName.getText().toString();
					final String loginPass = loginPassWord.getText().toString();
					if (loginUser.isEmpty() || loginPass.isEmpty()) {
						Toast.makeText(MainActivity.this, "No User", Toast.LENGTH_SHORT).show();
					} else {
						if (loginUser.equals(userFromSP) && loginPass.equals(passFromSP)) {
							Toast.makeText(MainActivity.this, " Successfully Logged in!!", Toast.LENGTH_SHORT).show();
							startActivity(new Intent(MainActivity.this, UserActivity.class));
						} else
							Toast.makeText(MainActivity.this, "Incorrect username or Password!", Toast.LENGTH_SHORT).show();
						dialog.cancel();
					}
				}
			});
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			AlertDialog dialog = builder.create();
			dialog.show();
		}
		else if (v == tempGo){
			Toast.makeText(MainActivity.this, "Temporary for checking!", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, UserActivity.class));
		}
	}
}
