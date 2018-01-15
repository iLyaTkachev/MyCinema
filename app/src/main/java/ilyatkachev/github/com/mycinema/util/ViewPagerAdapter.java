package ilyatkachev.github.com.mycinema.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    private FragmentManager mFragmentManager;

    public ViewPagerAdapter(final FragmentManager pFragmentManager) {
        super(pFragmentManager);
        mFragmentManager = pFragmentManager;
    }

    public void addFragment(final Fragment pFragment, final String pTitle) {
        mFragments.add(pFragment);
        mFragmentTitles.add(pTitle);
    }

    @Override
    public Fragment getItem(final int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return mFragmentTitles.get(position);
    }
}