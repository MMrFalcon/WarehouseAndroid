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
import com.falcon.warehouse.adapter.ProductLocalisationAdapter;
import com.falcon.warehouse.contract.IProductLocalisationListContract;
import com.falcon.warehouse.entity.ProductLocalisation;
import com.falcon.warehouse.root.App;
import com.falcon.warehouse.root.Constants;

import java.util.List;

import javax.inject.Inject;

public class ProductLocalisationListFragment extends Fragment implements IProductLocalisationListContract.View {

    private RecyclerView recyclerView;

    @Inject
    ProductLocalisationAdapter productLocalisationAdapter;

    @Inject
    IProductLocalisationListContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);

        View fragmentView = inflater.inflate(R.layout.recycle_layout, container, false);

        presenter.attachView(this);
        productLocalisationAdapter.attachFragment(this);

        recyclerView = fragmentView.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(productLocalisationAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String scanType = bundle.getString(Constants.SCAN_TYPE_KEY);

            if (scanType != null && scanType.equals(Constants.SCAN_LOCALISATION_KEY)) {
                presenter.fillListByLocalisationIndex(bundle.getString(Constants.PROD_LOCALISATION_INDEX_KEY));
            } else if (scanType != null && scanType.equals(Constants.SCAN_PRODUCT_KEY)) {
                presenter.fillListByProductIndex(bundle.getString(Constants.PROD_PRODUCT_INDEX_KEY));
            } else {
                presenter.fillList();
            }
        } else {
            presenter.fillList();
        }

        return fragmentView;
    }

    public IProductLocalisationListContract.Presenter getPresenter() {
        return this.presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        //refresh list from api
        presenter.fillList();
        productLocalisationAdapter.attachFragment(this);
        presenter.attachView(this);
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
    public void addItems(LiveData<List<ProductLocalisation>> items) {
        items.observe(this, productLocalisations -> {
            if (productLocalisations != null) {
                productLocalisationAdapter.addItems(productLocalisations);
            }
        });
    }
}
