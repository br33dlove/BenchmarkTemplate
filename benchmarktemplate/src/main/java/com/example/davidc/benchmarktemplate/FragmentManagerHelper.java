package com.example.davidc.benchmarktemplate;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

class FragmentManagerHelper {

    static boolean noFragmentBoundToView(
            final FragmentManager fragmentManager,
            @IdRes final int view
    ) {
        return fragmentManager.findFragmentById(view) == null;
    }

    static void addFragment(
            final FragmentManager fragmentManager,
            final Fragment fragment,
            @IdRes final int view
    ) {
        fragmentManager
                .beginTransaction()
                .add(view, fragment)
                .addToBackStack(null)
                .commit();
    }

    static boolean hasMoreThanOneNonRetainedFragment(final FragmentManager fragmentManager) {
        return nonRetainedFragmentCount(fragmentManager) > 1;
    }

    private static int nonRetainedFragmentCount(final FragmentManager fragmentManager) {
        int nonRetainedFragmentCount = 0;
        for (final Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null && !fragment.getRetainInstance()) {
                nonRetainedFragmentCount++;
            }
        }
        return nonRetainedFragmentCount;
    }
}
