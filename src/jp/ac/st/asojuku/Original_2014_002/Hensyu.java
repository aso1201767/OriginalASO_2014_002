package jp.ac.st.asojuku.Original_2014_002;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Hensyu extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

	// SQLiteデータベース空間を操作するインスタンス変数を宣言
	SQLiteDatabase sdb = null;
	// MySQLiteOpenHelperを操作するインスタンス変数を宣言
	MySQLiteOpenHelper helper =null;

	// リストにて選択したHitokotoテーブルのレコードの[_id]カラム値を保持する変数の宣言
	int selectedID = -1;
	// リストにて選択した行番号を保持する変数の宣言
	int lastPosition = -1;

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
		Button btndelete = (Button)findViewById(R.id.btnDelete);
		ListView lstHitokoto = (ListView)findViewById(R.id.lstHitokoto);

		btnback.setOnClickListener(this);
		btndelete.setOnClickListener(this);
		// ListViewにOnClickListenerをセット
		lstHitokoto.setOnItemClickListener(this);

		//ListViewにOnItemClickListenerをセット
		lstHitokoto.setOnItemClickListener(this);

		// ListViewにDBの値をセット
		this.setDBValuetoList(lstHitokoto);
	}

	private void setDBValuetoList(ListView lstHitokoto) {
		SQLiteCursor cursor = null;

		// クラスのフィールド変数がNULLなら、データベース空間オープン
		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLException e){
			Log.e("ERROR",e.toString());
		}
		// MySQLiteOpenHelperにSELECT文を実行させて結果のカーソルを受け取る
		cursor = this.helper.selectHitokotoList(sdb);

		// dblayout: ListViewにさらにレイアウトを指定するもの
		int db_layout = android.R.layout.simple_list_item_activated_1;
		// from: カーソルからListViewに指定するカラムの値を指定するもの
		String[]from = {"phrase"};
		// to: Listviewの中の指定したdb_layoutに配置する、各行のview部品のid
		int[] to = new int[]{android.R.id.text1};

		//ListViewにセットするアダプターを生成
		//カーソルをもとに、fromの列から、toのViewへ値のマッピングが行われる。
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

		// アダプターを設定する
		lstHitokoto.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		 case R.id.btnback:
			 //今の画面Activityを消して、前の画面Activityに戻る
			 finish();
			 break;
		 case R.id.btnDelete:
			 //選択行があれば
			 if(this.selectedID != -1){
				 this.deleteFromHitokoto(this.selectedID);
				 ListView lstHitokoto = (ListView)findViewById(R.id.lstHitokoto);
				 //ListViewにDBをセット
				 this.setDBValuetoList(lstHitokoto);
				 //選択行を忘れる
				 this.selectedID = -1;
				 this.lastPosition = -1;
			 }else {
				 //なければ、トースト（メッセージ）を表示
				 Toast.makeText(Hensyu.this, "削除する行を選んでください", Toast.LENGTH_SHORT).show();
			 }
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ

		//前に選択中の行があれば、背景色を透明にする
		if(this.selectedID!=-1){
			parent.getChildAt(this.lastPosition).setBackgroundColor(0);
		}
		//選択中の行の背景色をグレーにする
		view.setBackgroundColor(android.graphics.Color.LTGRAY);

		//選択行のレコードを指し示すカーソルを取得
		SQLiteCursor cursor = (SQLiteCursor)parent.getItemAtPosition(position);
		//カーソルのレコードから、[_id]の値を取得して記憶
		this.selectedID = cursor.getInt(cursor.getColumnIndex("_id"));
		//何行目を選択したかも記憶
		this.lastPosition = position;
	}

	private void deleteFromHitokoto(int id){
		//クラスのフィールド変数がNULLなら、データベース空間オープン
		if(sdb==null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			Log.e("ERROR",e.toString());
		}
		//MySQLiteOpenHelperにDELETE文を実行させる
		this.helper.deleteHitokoto(sdb, id);
	}
}
