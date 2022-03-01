package com.unicode.app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.unicode.app.Adapters.FragmentAdapterOrder;
import com.unicode.app.R;
import com.google.android.material.tabs.TabLayout;

public class OrderActivity extends AppCompatActivity {
    ImageView clothIcon_iv;
    TextView clothTitle_tv;
    private String clothTitle;
    String[] tabsOrder;
    int clothIcon;
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    FragmentAdapterOrder fragmentAdapterOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        clothIcon_iv = findViewById(R.id.imageViewOrder);
        clothTitle_tv = findViewById(R.id.titleTextViewOrder);
        viewPager2 = findViewById(R.id.viewPagerOrder);
        tabLayout = findViewById(R.id.tabLayoutOrder);

        tabsOrder = getResources().getStringArray(R.array.tab_names_order);

        //Receiving icon
        Bundle bundle = getIntent().getExtras();
        clothIcon = bundle.getInt("icon_cloth");
        clothIcon_iv.setImageResource(clothIcon);

        //Receiving title
        clothTitle = getIntent().getStringExtra("dress_type");
        clothTitle_tv.setText(clothTitle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapterOrder = new FragmentAdapterOrder(fragmentManager, getLifecycle());
        viewPager2.setAdapter(fragmentAdapterOrder);

        tabLayout.addTab(tabLayout.newTab().setText("Sizes"));
        tabLayout.addTab(tabLayout.newTab().setText("Precisions"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}