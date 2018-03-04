package com.xdw;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener {
    private TextView tvIndex;
    private TextView tvLicai;
    private TextView tvFind;
    private TextView tvMine;
    private FrameLayout flContainer;
    private IndexFragment indexFragment;
    private LicaiFragment licaiFragment;
    private FindFragment findFragment;
    private MineFragment mineFragment;
    private Button logout;
    private SlidingMenu menu;
    private ImageView btnToggle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setBehindContentView(R. layout.fragment_menu );
        setContentView(R.layout.activity_main);
        tvIndex = (TextView) findViewById(R.id.tv_index);
        tvLicai = (TextView) findViewById(R.id.tv_licai);
        tvFind = (TextView) findViewById(R.id.tv_find);
        tvMine = (TextView) findViewById(R.id.tv_mine);
        flContainer = (FrameLayout) findViewById(R.id.container);
        btnToggle= (ImageView) findViewById(R.id.btn_toggle);
        logout= (Button) findViewById(R.id.logout);
        indexFragment = new IndexFragment();
        licaiFragment = new LicaiFragment();
        findFragment = new FindFragment();
        mineFragment = new MineFragment();
        tvIndex.setOnClickListener(this);
        tvLicai.setOnClickListener(this);
        tvFind.setOnClickListener(this);
        tvMine.setOnClickListener(this);
        logout.setOnClickListener(this);
        btnToggle.setOnClickListener(this);
        initMenu();
        setIndexFragment();
    }

    private void initMenu() {
        menu = getSlidingMenu();
        menu.setShadowWidthRes(R.dimen.activity_horizontal_margin);
        menu.setBehindOffsetRes(R.dimen.shadow);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeDegree(0.5f);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_index:
                btnToggle.setVisibility(View.GONE);
                setIndexFragment();
                break;
            case R.id.tv_licai:
                btnToggle.setVisibility(View.GONE);
                setLicaiFragment();
                break;
            case R.id.tv_find:
                btnToggle.setVisibility(View.GONE);
                setFindFragment();
                break;
            case R.id.tv_mine:
                btnToggle.setVisibility(View.VISIBLE);
                setMineFragment();
                break;
            case R.id.logout:
                Toast.makeText(this,"我是侧滑菜单",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_toggle:
                menu.toggle();
                break;
        }
    }

    private void setMineFragment() {
        menu.setTouchModeAbove(SlidingMenu.LEFT);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (!mineFragment.isAdded()){
            transaction.add(R.id.container,mineFragment);
            transaction.show(mineFragment).hide(indexFragment).hide(licaiFragment).hide(findFragment);
        }else{
            transaction.show(mineFragment).hide(indexFragment).hide(licaiFragment).hide(findFragment);
        }
        transaction.commit();
    }

    private void setFindFragment() {
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (!findFragment.isAdded()){
            transaction.add(R.id.container,findFragment);
            transaction.show(findFragment).hide(indexFragment).hide(licaiFragment).hide(mineFragment);
        }else{
            transaction.show(findFragment).hide(indexFragment).hide(licaiFragment).hide(mineFragment);
        }
        transaction.commit();
    }

    private void setLicaiFragment() {
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (!licaiFragment.isAdded()){
            transaction.add(R.id.container,licaiFragment);
            transaction.show(licaiFragment).hide(indexFragment).hide(mineFragment).hide(findFragment);
        }else{
            transaction.show(licaiFragment).hide(indexFragment).hide(mineFragment).hide(findFragment);
        }
        transaction.commit();
    }

    private void setIndexFragment() {
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (!indexFragment.isAdded()){
            transaction.add(R.id.container,indexFragment);
            transaction.show(indexFragment).hide(mineFragment).hide(licaiFragment).hide(findFragment);
        }else{
            transaction.show(indexFragment).hide(mineFragment).hide(licaiFragment).hide(findFragment);
        }
        transaction.commit();
    }
}
