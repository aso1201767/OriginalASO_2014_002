package jp.ac.st.asojuku.Original_2014_002;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class HitokotoView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);

		setContentView(R.layout.hitokotoview);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		//画面(Activity)に渡されたインテントを取得
		Intent intent = this.getIntent();
		//intentから、Extaraに混入させた値をキーワードで取得
		String strHitokoto = intent.getStringExtra("hitokoto");
		//取得したStringを、TxtViewにセット
		TextView txvHITOKOTO = (TextView)findViewById(R.id.txthitokoto);
		txvHITOKOTO.setText(strHitokoto);
	}

}
