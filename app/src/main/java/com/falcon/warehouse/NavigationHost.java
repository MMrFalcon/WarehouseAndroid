package com.falcon.warehouse;

import androidx.fragment.app.Fragment;


public interface NavigationHost {
    void navigateTo(Fragment fragment, boolean addToBackStack);
}

