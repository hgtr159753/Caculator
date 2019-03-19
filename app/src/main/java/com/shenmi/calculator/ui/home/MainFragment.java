package com.shenmi.calculator.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shenmi.calculator.R;
import com.shenmi.calculator.constant.ADConstant;
import com.shenmi.calculator.ui.CalculatorActivity;
import com.shenmi.calculator.ui.LoanActivity;
import com.shenmi.calculator.ui.UnitConvertActivity;
import com.shenmi.calculator.ui.UpperNumActivity;
import com.shenmi.calculator.util.AppMarketUtil;
import com.shenmi.calculator.util.SPUtil;
import com.shenmi.calculator.util.UnitConvertUtil;
import com.sm.readbook.base.BaseFragment;
import com.snmi.sdk.AdView;
import com.snmi.sdk.BannerListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.tencent.open.utils.Global.getPackageName;

/**
 * Created by SQ on 2018/12/18.
 * 计算器主页
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout rl_capital;
    private RelativeLayout rl_science;
    private RelativeLayout rl_length;
    private RelativeLayout rl_area;
    private RelativeLayout rl_volume;
    private RelativeLayout rl_temperature;
    private RelativeLayout rl_speed;
    private RelativeLayout rl_time;
    private RelativeLayout rl_mass;
    private AdView rl_banner;
    private Handler mStartSMSDKHandler = new Handler();

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(R.layout.activity_functionset_choose, container,false);
            initView(mRootView);
            setListener();
        }
        return mRootView;
    }

    protected void initView(View view) {
        rl_capital = view.findViewById(R.id.rl_capital);
        rl_science = view.findViewById(R.id.rl_science);
        rl_length = view.findViewById(R.id.rl_length);
        rl_area = view.findViewById(R.id.rl_area);
        rl_volume = view.findViewById(R.id.rl_volume);
        rl_temperature = view.findViewById(R.id.rl_temperature);
        rl_speed = view.findViewById(R.id.rl_speed);
        rl_time = view.findViewById(R.id.rl_time);
        rl_mass = view.findViewById(R.id.rl_mass);
        rl_banner = view.findViewById(R.id.rl_banner);
    }

    protected void setListener() {
        rl_capital.setOnClickListener(this);
        rl_science.setOnClickListener(this);
        rl_length.setOnClickListener(this);
        rl_volume.setOnClickListener(this);
        rl_temperature.setOnClickListener(this);
        rl_speed.setOnClickListener(this);
        rl_area.setOnClickListener(this);
        rl_time.setOnClickListener(this);
        rl_mass.setOnClickListener(this);
        boolean isOpen = (Boolean) SPUtil.get(getActivity(), ADConstant.ISOPENAD, false);
        if (isOpen){
//            //有广告则调用申米广告sdk
            rl_banner.setVisibility(View.VISIBLE);
            rl_banner.setAdListener(new BannerMonitor(ADConstant.BANNER_ONE, getActivity()));
        }else{
            rl_banner.setVisibility(View.GONE);
        }
    }

    public class BannerMonitor implements BannerListener {
        private String loactionID;
        private Context mContext;

        public BannerMonitor(String locationID, Context context) {
            this.loactionID = locationID;
            this.mContext = context;
        }

        @Override
        public void bannerClicked() {

        }

        @Override
        public void adpageClosed() {

        }

        @Override
        public void bannerClosed() {

        }

        @Override
        public void bannerShown(String json) {

        }

        @Override
        public void noAdFound() {

        }

        @Override
        public void qtClicked(String s) {

        }
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = null;
        switch (v.getId()) {
            case R.id.rl_capital:
                mIntent = new Intent(getActivity(),
                        UpperNumActivity.class);
                break;
            case R.id.rl_science:
                mIntent = new Intent(getActivity(),
                        CalculatorActivity.class);
                break;
            case R.id.rl_length:
                mIntent = new Intent(getActivity(),
                        UnitConvertActivity.class);
                mIntent.putExtra("UnitType", UnitConvertUtil.LENGTH);
                break;
            case R.id.rl_area:
                mIntent = new Intent(getActivity(),
                        UnitConvertActivity.class);
                mIntent.putExtra("UnitType", UnitConvertUtil.AREA);
                break;
            case R.id.rl_volume:
                mIntent = new Intent(getActivity(),
                        UnitConvertActivity.class);
                mIntent.putExtra("UnitType", UnitConvertUtil.VOLUME);
                break;
            //贷款计算器
            case R.id.rl_temperature:
                mIntent = new Intent(getActivity(),
                        LoanActivity.class);
                break;
            case R.id.rl_speed:
                mIntent = new Intent(getActivity(),
                        UnitConvertActivity.class);
                mIntent.putExtra("UnitType", UnitConvertUtil.SPEED);
                break;
            case R.id.rl_time:
                mIntent = new Intent(getActivity(),
                        UnitConvertActivity.class);
                mIntent.putExtra("UnitType", UnitConvertUtil.TIME);
                break;
            case R.id.rl_mass:
                mIntent = new Intent(getActivity(),
                        UnitConvertActivity.class);
                mIntent.putExtra("UnitType", UnitConvertUtil.MASS);
                break;
            default:
                break;
        }
        if (mIntent != null) {
            startActivity(mIntent);
        }
    }
}
