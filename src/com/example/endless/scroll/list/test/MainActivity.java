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

         //ó������ ȭ�鿡 ���� ������ ���� �� �������� ���� && �� ������ 100���� ������ �����͸� �� load �մϴ�. 
        if(loadMore && totalCount < 100) {
        	//�����͸� 10�� �� �ε��մϴ�
            adapter.count += 10; 
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView v, int s) { }    

    class Aleph0 extends BaseAdapter {
    	//�� ó�� �ε��ϴ� ������ ������ �����մϴ�.
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