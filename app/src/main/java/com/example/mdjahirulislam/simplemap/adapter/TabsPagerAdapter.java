package com.example.mdjahirulislam.simplemap.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mdjahirulislam.simplemap.fragment.ShowExpenseFragment;
import com.example.mdjahirulislam.simplemap.fragment.ShowMomentFragment;


public class TabsPagerAdapter extends FragmentPagerAdapter {

	int mNumberOfTabs;
	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
//		this.mNumberOfTabs = NumOfTabs;
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
			case 0:
				// Top Rated fragment activity
				return new ShowExpenseFragment();
			case 1:
				// Games fragment activity
				return new ShowMomentFragment();
//            case 2:
//                // Top Rated fragment activity
//                return new ForecastWeatherFragment();

			default:
				return null;

		}
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position){
			case 0:
				return "Expense";
			case 1:
				return "Moment";
//			case 2:
//				return "Forecast";
		}
		return super.getPageTitle(position);
	}
}
