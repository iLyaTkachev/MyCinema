package ilyatkachev.github.com.mycinema.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ilyatkachev.github.com.mycinema.R;
import ilyatkachev.github.com.mycinema.util.Constants;
import ilyatkachev.github.com.mycinema.util.Injection;
import ilyatkachev.github.com.mycinema.util.ViewPagerAdapter;

public class ViewPagerWrapper extends Fragment {

    private ViewPager mViewPager;
    private MediaType mMediaType;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_wrapper, null);
        mMediaType = (MediaType) getArguments().getSerializable(Constants.TYPE_KEY);
        mViewPager = view.findViewById(R.id.view_pager);
        if (mViewPager != null) {
            setupViewPager(mViewPager);
        }
        final TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setRetainInstance(true);
        return view;
    }

    private void setupViewPager(final ViewPager pViewPager) {
        //during rotation adapter first check FragementManager, if there no such fragment, then it takes fragment from the list
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        for (int i = 0; i < mMediaType.getFilterTypes().length; i++) {
            MediaFilterType mediaFilterType = mMediaType.getFilterTypes()[i];
            adapter.addFragment(createFragmentWithPresenter(mMediaType, mediaFilterType), mediaFilterType.toString());
        }
        pViewPager.setAdapter(adapter);
    }

    private Fragment createFragmentWithPresenter(final MediaType pMediaType, final MediaFilterType pFilterType) {
        final MediaListFragment fragment = new MediaListFragment();
        final MediaPresenter mediaPresenter = new MediaPresenter(
                fragment,
                Injection.provideGetMedia(getActivity().getApplicationContext()),
                Injection.provideGetFavoriteMedia(getActivity().getApplicationContext()),
                Injection.provideAddFavoriteMedia(getActivity().getApplicationContext()),
                Injection.provideUseCaseHandler(),
                pMediaType,
                pFilterType);

        return fragment;
    }
}
