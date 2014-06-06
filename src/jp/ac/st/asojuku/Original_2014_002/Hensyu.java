package jp.ac.st.asojuku.Original_2014_002;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Hensyu extends Activity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);

		setContentView(R.layout.hennsyu);
	}

	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		Button btnback =(Button)findViewById(R.id.btnback);
		btnback.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		 case R.id.btnback:
			 intent =  new Intent(Hensyu.this, MainActivity.class);
			 startActivity(intent);
			 break;
		}
	}
}
