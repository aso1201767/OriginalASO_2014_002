package jp.ac.st.asojuku.Original_2014_002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity  implements View.OnClickListener{

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;

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

		//クラスのフィールド変数がNULLなら、データベース空間オープン
		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			//異常終了
			return;
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.btnSubmit:
			//エディットテキストからの入力内容を取り出す
			EditText etv = (EditText)findViewById(R.id.edtHitokoto);
			String inputMsg = etv.getText().toString();

			//inputMsgがnullでない、かつ、空でない場合のみ、if文内を実行
			if(inputMsg!=null && !inputMsg.isEmpty()){
				//MySQLiteOpenHelperのインサートメソッドを呼び出し
				helper.insertHitokoto(sdb, inputMsg);
			}
			//入力欄をクリア
			etv.setText("");
			break;
		case R.id.btnMente:
			intent = new Intent(MainActivity.this, Hensyu.class);
			startActivity(intent);
			break;
		case R.id.btntyekku:
			//MySQLiteOpenHeloperのセレクト一言メソッドを呼び出して一言をランダムに習得
			String strHitokoto = helper.selectRandomHitokoto(sdb);
			//インテントのインスタンス生成
			intent = new Intent(MainActivity.this, HitokotoView.class);
			//インテントに一言を混入
			intent.putExtra("hitokoto", strHitokoto);
			//次画面のアクティビティ起動
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
