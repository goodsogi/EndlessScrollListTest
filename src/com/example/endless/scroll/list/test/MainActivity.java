package com.example.endless.scroll.list.test;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity implements OnScrollListener {

    Aleph0 adapter = new Aleph0();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(adapter); 
        getListView().setOnScrollListener(this);
    }

    @Override
    public void onScroll(AbsListView view,
        int firstVisible, int visibleCount, int totalCount) {
    	
    	Log.i("scroll", "firstVisible: " + firstVisible + "    visibleCount: " + visibleCount + "       totalCount: " + totalCount);

        boolean loadMore = /* maybe add a padding */
            firstVisible + visibleCount >= totalCount;

         //처음부터 화면에 보인 개수의 합이 총 개수보다 적고 && 총 개수가 100보다 적으면 데이터를 더 load 합니다. 
        if(loadMore && totalCount < 100) {
        	//데이터를 10개 더 로딩합니다
            adapter.count += 10; 
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView v, int s) { }    

    class Aleph0 extends BaseAdapter {
    	//맨 처음 로딩하는 데이터 개수를 지정합니다.
        int count = 10; 

        public int getCount() { return count; }
        public Object getItem(int pos) { return pos; }
        public long getItemId(int pos) { return pos; }

        @Override
        public View getView(int pos, View v, ViewGroup p) {
                TextView view = new TextView(MainActivity.this);
                view.setText("entry " + pos);
                return view;
        }
		
    }

	
}