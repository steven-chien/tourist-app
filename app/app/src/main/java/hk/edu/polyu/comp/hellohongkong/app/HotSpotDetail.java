package hk.edu.polyu.comp.hellohongkong.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by zhoumoyuan on 4/14/16.
 */
public class HotSpotDetail extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_spot_detail);
        TextView t = (TextView)findViewById(R.id.id);
        if (t != null) {
            t.setText(getIntent().getStringExtra("id"));
        }
        t = (TextView)findViewById(R.id.name);
        if (t != null) {
            t.setText(getIntent().getStringExtra("name"));
        }

    }
}
