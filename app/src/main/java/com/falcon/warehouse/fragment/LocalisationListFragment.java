package com.falcon.warehouse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.falcon.warehouse.R;
import com.falcon.warehouse.adapter.LocalisationAdapter;
import com.falcon.warehouse.contract.ILocalisationListContract;
import com.falcon.warehouse.entity.Localisation;
import com.falcon.warehouse.root.App;

import java.util.List;

import javax.inject.Inject;

public class LocalisationListFragment extends Fragment implements ILocalisationListContract.View {

    private RecyclerView recyclerView;

    @Inject
    LocalisationAdapter localisationAdapter;

    @Inject
    ILocalisationListContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);

        View fragmentView = inflater.inflate(R.layout.recycle_layout, container, false);

        presenter.attachView(this);
        presenter.fillList();

        recyclerView = fragmentView.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(localisationAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        localisationAdapter.attachFragment(this);

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        localisationAdapter.attachFragment(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView(this);
    }

    @Override
    public void addItems(LiveData<List<Localisation>> localisations) {
        localisations.observe(this, localisations1 -> {
            if (localisations1 != null) {
                localisationAdapter.addItems(localisations1);
            }
        });
    }
}
