package com.ja.assets.new_code.bussiness.inventory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.base.BaseJavaFragmentActivity;
import com.ja.assets.new_code.bussiness.bean.post.PandianbaobiaoBasePostBean;
import com.ja.assets.new_code.bussiness.bean.result.PandianBaseResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.util.DensityUtil;
import com.ja.assets.utils.ACacheUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ZichanbaobiaoActivity extends FragmentActivity {
    View iv_back;


    TextView tv_danhao;
    TextView tv_pandianbumen;
    TextView tv_pandianren;
    TextView tv_zhuangtai;
    TextView tv_pandianshijian;
    TextView tv_pandianleixing;
    TextView tv_zichanzongshu;
    TextView tv_pandianjieguo;
    TextView tv_shifoufupan;


    public TabLayout tl_tab;
    ViewPager vp_content;
    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zichanbaobiao);
        initView();
    }

    void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tl_tab = (TabLayout) findViewById(R.id.tl_tab);
        vp_content = (ViewPager) findViewById(R.id.vp_content);
        id = getIntent().getIntExtra("id", -1);
        initContent();
        initTab();
        initPandian_base();
    }

    public int id;

    void initPandian_base() {
        tv_danhao = findViewById(R.id.tv_danhao);
        tv_pandianbumen = findViewById(R.id.tv_pandianbumen);
        tv_pandianren = findViewById(R.id.tv_pandianren);
        tv_zhuangtai = findViewById(R.id.tv_zhuangtai);
        tv_pandianshijian = findViewById(R.id.tv_pandianshijian);
        tv_pandianleixing = findViewById(R.id.tv_pandianleixing);
        tv_zichanzongshu = findViewById(R.id.tv_zichanzongshu);
        tv_pandianjieguo = findViewById(R.id.tv_pandianjieguo);
        tv_shifoufupan = findViewById(R.id.tv_shifoufupan);

        String token = ACacheUtil.getToken();
        PandianbaobiaoBasePostBean bean = new PandianbaobiaoBasePostBean();

        bean.id = id;
        ApiUtils.getApiService().checkReport_base(token, bean).enqueue(new JuanCallback<BaseBean<PandianBaseResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<PandianBaseResultBean>> response, BaseBean<PandianBaseResultBean> message) {
                if (message.code == 0) {
                    tv_danhao.setText(message.data.checkNum);
                    tv_pandianbumen.setText(message.data.syDeptName);
                    tv_pandianren.setText(message.data.checkUserName);
                    tv_zhuangtai.setText(message.data.statusFlag);
                    tv_pandianshijian.setText(message.data.checkTime);
                    if (message.data.profit == 1) {
                        tv_pandianleixing.setText("实物盘点");
                    } else {
                        tv_pandianleixing.setText("账务盘点");

                    }
                    tv_zichanzongshu.setText(message.data.total + "");
                    tv_pandianjieguo.setText(message.data.strResult);
                    tv_shifoufupan.setText(message.data.reCheckFlag);
                }
            }

            @Override
            public void onFail(Call<BaseBean<PandianBaseResultBean>> call, Throwable t) {

            }
        });
    }

    private void initTab() {
        tl_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tl_tab.setSelectedTabIndicatorColor(ContextCompat.getColor(ZichanbaobiaoActivity.this, R.color.SelectedTabIndicatorColor));
        tl_tab.setSelectedTabIndicatorHeight(DensityUtil.dip2px(ZichanbaobiaoActivity.this, 2));
        tl_tab.setTabTextColors(ContextCompat.getColor(ZichanbaobiaoActivity.this, R.color.UnSelectedTextColor), ContextCompat.getColor(ZichanbaobiaoActivity.this, R.color.SelectedTextColor));
        tl_tab.setBackgroundColor(ContextCompat.getColor(ZichanbaobiaoActivity.this, R.color.white));
//        tl_tab.setTabTextColors(ContextCompat.getColor(this, R.color.gray), ContextCompat.getColor(this, R.color.white));
//        tl_tab.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
//        ViewCompat.setElevation(tl_tab, 10);
        tl_tab.setupWithViewPager(vp_content);
        changeTabIndicatorWidth(tl_tab, 15);

//        tl_tab.getTabAt(selectTab).select();
//        tl_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case 0:
//                        zhongyitizhiFragment1.initData();
//                        break;
//                    case 1:
//                        jichudaixieFragment.initData();
//                        break;
//                    case 2:
//                        fukejiankangFragment.initData();
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }

    /**
     * 改变tablayout指示器的宽度
     *
     * @param tabLayout
     * @param margin
     */
    public void changeTabIndicatorWidth(final TabLayout tabLayout, final int margin) {
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
                    mTabStripField.setAccessible(true);

                    LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);

                    int dp10 = margin == 0 ? 50 : margin;

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }


    //    SecretTabFragment piweiganshen;
    ZichanbaobiaoFragment yingZichanbaobiaoFragment;
    ZichanbaobiaoFragment kuiZichanbaobiaoFragment;

    private void initContent() {
        tabIndicators = new ArrayList<>();

        tabIndicators.add("盘盈资产");
        tabIndicators.add("盘亏资产");


        tabFragments = new ArrayList<>();
        yingZichanbaobiaoFragment = new ZichanbaobiaoFragment();
        yingZichanbaobiaoFragment.type = 1;
        yingZichanbaobiaoFragment.id = id;
        kuiZichanbaobiaoFragment = new ZichanbaobiaoFragment();
        kuiZichanbaobiaoFragment.type = 0;
        kuiZichanbaobiaoFragment.id = id;
//        jichudaixieFragment = new DoctorsFenleiFragment();
//        jichudaixieFragment.type = "4";
//        fukejiankangFragment = new DoctorsFenleiFragment();
//        fukejiankangFragment.type = "99";
//        zhongyitizhiFragment1 = new DoctorsFenleiFragment();
//        zhongyitizhiFragment1.type = "3";


        tabFragments.add(yingZichanbaobiaoFragment);
        tabFragments.add(kuiZichanbaobiaoFragment);


        contentAdapter = new ContentPagerAdapter((this).getSupportFragmentManager());
        vp_content.setAdapter(contentAdapter);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }

}
