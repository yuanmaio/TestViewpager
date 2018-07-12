package com.example.testviewpager;

import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public BannerHandler handler = new BannerHandler(new WeakReference<MainActivity>(this));
    public ViewPagerSlide viewPagerSlide;
    private List<String> list;
    private int currentItem;
     private CircleIndicator circleIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detailheader);
        viewPagerSlide = findViewById(R.id.viewpager);
        circleIndicator=findViewById(R.id.indicator_default);
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i + "");
        }
        NewsPicAdapter newsPicAdapter = new NewsPicAdapter(this, list);
        viewPagerSlide.setAdapter(newsPicAdapter);
        circleIndicator.setViewPager(viewPagerSlide, list.size());
        handler.sendEmptyMessageDelayed(BannerHandler.MSG_BREAK_SILENT, BannerHandler.MSG_DELAY);
        viewPagerSlide.setOnPageChangeListener(new MyPageChangeListener(viewPagerSlide));
//        viewPagerSlide.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_UP:
//                        handler.sendEmptyMessageDelayed(BannerHandler.MSG_BREAK_SILENT, BannerHandler.MSG_DELAY);
//                        break;
//                    case MotionEvent.ACTION_DOWN:
//                        handler.sendEmptyMessageDelayed(BannerHandler.MSG_KEEP_SILENT, BannerHandler.MSG_DELAY);
//                        break;
//                }
//                return false;
//            }
//        });
    }

    class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;
     //   private TextView text_title;
        private ViewPager viewpager;

        public MyPageChangeListener(ViewPager viewpager) {
         //   this.text_title = text_title;
            this.viewpager = viewpager;
        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            int index = position % list.size();
            currentItem = index;
            //  text_title.setText(header_titles.get(index));
            // dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            //  dots.get(index).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = index;
            // mAutoRun=null;
            handler.sendMessage(Message.obtain(handler,
                    BannerHandler.MSG_PAGE_CHANGED, position, 0));
        }

        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    handler.sendEmptyMessage(BannerHandler.MSG_KEEP_SILENT);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    handler.sendEmptyMessageDelayed(BannerHandler.MSG_UPDATE_IMAGE,
                            BannerHandler.MSG_DELAY);
                    break;
                default:
                    break;
            }
        }
    }
}
