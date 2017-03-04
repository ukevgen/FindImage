package com.internship.pbt.findimage.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.internship.pbt.findimage.R;
import com.internship.pbt.findimage.adapter.TabsFragmentAdapter;
import com.internship.pbt.findimage.view.fragment.favorites.FavoritesFragment;
import com.internship.pbt.findimage.view.fragment.results.ResultsFragment;

/**
 * Created by ukevgen on 04.03.2017.
 */

public class TabsFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    ViewPager pager;
    TabsFragmentAdapter adapter;
    TabLayout tabLayout;

    public TabsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);
        pager = (ViewPager) rootView.findViewById(R.id.view_pager);
        adapter = new TabsFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new ResultsFragment(), getString(R.string.results_fragment_title));
        adapter.addFragment(new FavoritesFragment(), getString(R.string.favorites_fragment_title));
        pager.setAdapter(adapter);

        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setCustomView(getTabIndicator(
                getActivity(), R.string.results_fragment_title, R.drawable.search_image_selector));
        tabLayout.getTabAt(1).setCustomView(getTabIndicator(
                getActivity(), R.string.favorites_fragment_title, R.drawable.favorites_selector));

        return rootView;
    }

    private View getTabIndicator(Context context, int title, int icon) {
        TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.tab_header, null);
        view.setText(title);
        view.setCompoundDrawablesWithIntrinsicBounds(0, icon, 0, 0);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tabLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        tabLayout.setVisibility(View.GONE);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 1)
            adapter.getItem(1).onResume();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
