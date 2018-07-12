package com.example.testviewpager;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


import java.util.List;

/**
 * Created by Administrator on 2016/1/12.
 */
public class NewsPicAdapter extends PagerAdapter {
    private Context context;
 private List<String> focusBeanList;
  //  DisplayImageOptions options;
  //  protected ImageLoader imageLoader;// 加载图片类
  //  private JJHomeBean.DataBean.IndexDataBean.CarouselBean focusBean;
    private LayoutInflater listContainer;//视图容器
    private ListItemView listItemView;

    public NewsPicAdapter(Context context, List<String> focusBeanList) {
        this.context = context;
        this.focusBeanList = focusBeanList;
     //   imageLoader = ImageLoader.getInstance();// 加载图片类
       // this.options = new DisplayImageOptions.Builder()
            //    .bitmapConfig(Bitmap.Config.RGB_565)
             //   .imageScaleType(ImageScaleType.IN_SAMPLE_INT).cacheInMemory()
            //    .cacheOnDisc().build();
        listContainer = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        // 设置成最大，使用户看不到边界
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(View convertView, int position) {
        View view = null;
        if (view == null) {
            view = listContainer.inflate(R.layout.banner_item, null);
            listItemView = new ListItemView();
            listItemView.iv = (ImageView) view.findViewById(R.id.image);

            view.setTag(listItemView);
            System.out.println("listItemView.iv = " + listItemView.iv);
        } else {
            listItemView = (ListItemView) view.getTag();
        }
        ImageView imageView = listItemView.iv;
        position %= focusBeanList.size();
       // JJHomeBean.DataBean.IndexDataBean.CarouselBean nominate = focusBeanList.get(position);
        imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.main_bigtop));
      //  imageLoader.displayImage(nominate.getImg(), imageView, options);
        listItemView.iv.setTag(position);
        listItemView.iv.setOnClickListener(new click());
        ((ViewPager) convertView).addView(view);
        return view;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {

    }

    @Override
    public void finishUpdate(View arg0) {

    }

    private class click implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
          //  JJHomeBean.DataBean.IndexDataBean.CarouselBean focusBean = focusBeanList.get(position);
         //   if (focusBean != null) {
           //     String url = focusBean.getImg_url();
              //  MobclickAgent.onEvent(context, "116", "focus");
             //   if (url != null && !url.equals("")) {
               //     if (url.contains("http")) {
                   //     Uri uri = Uri.parse(url);
                     //   Intent intent = new Intent(context, WebViewActivity.class);
                     //     intent.putExtra("url",url);
                     //   context.startActivity(intent);
                 //   } else {
                       // UIHelper.showDetail(context, url, focusBean.getTitle(), "", "", "", "doc");
                  //  }
                }

            }



    static class ListItemView {
        private ImageView iv;
    }
}