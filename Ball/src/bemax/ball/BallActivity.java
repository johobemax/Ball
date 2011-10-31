package bemax.ball;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.SurfaceView;

public class BallActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SurfaceView sv = (SurfaceView)findViewById(R.id.sv);
        Resources rsc = this.getResources();	//このActivityのリソース
        BallMain bm = new BallMain(sv, rsc);

    }
}