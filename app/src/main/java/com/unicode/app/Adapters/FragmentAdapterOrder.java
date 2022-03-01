package com.unicode.app.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.unicode.app.Fragments.PrecisionsFragment;
import com.unicode.app.Fragments.SizesFragment;

public class FragmentAdapterOrder extends FragmentStateAdapter {
    String[] tabs = {"Sizes", "Precisions"};

    public FragmentAdapterOrder(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return new SizesFragment();
        if (position == 1)
            return new PrecisionsFragment();
        return null;
    }

    @Override
    public int getItemCount() {
        return tabs.length;
    }
}
