package com.test.renderScript;

import android.content.Context;
import android.renderscript.RSSurfaceView;
import android.renderscript.RenderScriptGL;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.util.Log;
import android.renderscript.RenderScript;

public class FallView extends RSSurfaceView {
	String TAG = "FallView";
	 
	private FallRS mRender;
	private RenderScriptGL mRs;
	public FallView(Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	public void surfaceCreated(SurfaceHolder holder) {
			super.surfaceCreated(holder);

			RenderScriptGL.SurfaceConfig sc = new RenderScriptGL.SurfaceConfig();
			if(mRs == null) {
					mRs = createRenderScriptGL(sc);
			}
			mRs.setPriority(RenderScript.Priority.LOW);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
			super.surfaceChanged(holder, format, w, h);
			Log.v(TAG, "Surface Changed!");
			//RenderScriptGL.SurfaceConfig sc = new RenderScriptGL.SurfaceConfig();
			//RenderScriptGL RS = createRenderScriptGL(sc);
			if(mRs != null) {
					mRs.setSurface(holder, w, h);
			}

			if(mRender == null) {
				mRender = new FallRS(w, h);
				mRender.init(mRs, getResources(), false);
				mRender.start();
			} else {
				mRender.resize(w, h);
			}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//case MotionEvent.ACTION_MOVE:
				mRender.addDrop(event.getX(), event.getY());
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) {
				// Ignore
				}
				break;
			}
		return true;
	}

	@Override  
	protected void onDetachedFromWindow()  
	{  
		super.onDetachedFromWindow();  
		destroyRenderer();  
	}  

	private void destroyRenderer() {  
			if (mRender != null) {  
					mRender.stop();  
					mRender = null;  
			}  
			if (mRs != null) {  
					mRs.setSurface(null, 0, 0);  
					mRs.destroy();  
					mRs = null;  
			}  
	}  
}
