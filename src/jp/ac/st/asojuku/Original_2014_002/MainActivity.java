package jp.ac.st.asojuku.Original_2014_002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity  implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		Button btnSubmit =(Button)findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(this);
		Button btnMente =(Button)findViewById(R.id.btnMente);
		btnMente.setOnClickListener(this);
		Button btntyekku =(Button)findViewById(R.id.btntyekku);
		btntyekku.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.btnSubmit:
			break;
		case R.id.btnMente:
			intent = new Intent(MainActivity.this, Hensyu.class);
			startActivity(intent);
			break;
		case R.id.btntyekku:
			intent = new Intent(MainActivity.this, HitokotoView.class);
			startActivity(intent);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
