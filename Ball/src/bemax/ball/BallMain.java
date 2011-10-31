package bemax.ball;

import java.util.Random;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnTouchListener;

public class BallMain implements Runnable, Callback, OnTouchListener{
	private SurfaceView sv;
	private Resources rsc;
	private SurfaceHolder holder;
	private boolean loop;
	private int x, y, dx, dy;
	private Bitmap bm;

	public BallMain(SurfaceView sv, Resources rsc) {
		this.sv = sv;
		this.rsc = rsc;
		holder = sv.getHolder();
		holder.addCallback(this);
		loop = true;
		sv.setOnTouchListener(this);
	}

	public void run() {
		//SurfaceViewへの描画をする。
		Paint pen1 = new Paint();
		Paint pen2 = new Paint();
		pen1.setColor(Color.RED);
		pen2.setColor(Color.WHITE);
		Random rand = new Random();
		bm = BitmapFactory.decodeResource(rsc, R.drawable.java);
		x = rand.nextInt(sv.getWidth() - bm.getWidth());
		y = rand.nextInt(sv.getHeight() - bm.getHeight());
		dx = 5; dy = 5;

		while(loop){
			Canvas canvas = holder.lockCanvas();
			canvas.drawRect(0,0,sv.getWidth(),sv.getHeight(),pen2);
			canvas.drawBitmap(bm, x, y, null);
			holder.unlockCanvasAndPost(canvas);
			x += dx;
			if(x < 0 || x > sv.getWidth() - bm.getWidth()){
				dx *= -1;
			}
			y += dy;
			if(y < 0 || y > sv.getHeight() - bm.getHeight()){
				dy *= -1;
			}
		}
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void surfaceCreated(SurfaceHolder holder) {
		Thread t = new Thread(this);
		t.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		loop = false;
	}

	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			float x = event.getX();
			float y = event.getY();
			float cx = this.x + bm.getWidth() / 2.0f;
			float cy = this.y + bm.getHeight() / 2.0f;
			double len = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
			if(len <= 50){
				Random rand = new Random();
				this.x = rand.nextInt(sv.getWidth() - bm.getWidth());
				this.y = rand.nextInt(sv.getHeight() - bm.getHeight());
				// 洋次朗さんがしゃべる処理

			}
		}
		return false;
	}

}
