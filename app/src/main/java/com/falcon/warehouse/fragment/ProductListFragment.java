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
import com.falcon.warehouse.adapter.ProductAdapter;
import com.falcon.warehouse.contract.IProductListContract;
import com.falcon.warehouse.entity.Product;
import com.falcon.warehouse.root.App;

import java.util.List;

import javax.inject.Inject;

public class ProductListFragment extends Fragment implements IProductListContract.View {

    private RecyclerView recyclerView;

    @Inject
    ProductAdapter productAdapter;

    @Inject
    IProductListContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);

        View fragmentView = inflater.inflate(R.layout.recycle_layout, container, false);

        presenter.attachView(this);
        presenter.fillList();

        productAdapter.attachFragment(this);

        recyclerView = fragmentView.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        productAdapter.attachFragment(this);
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
    public void addItems(LiveData<List<Product>> products) {
        products.observe(this, products1 -> {
            if (products1 != null) {
                productAdapter.addItems(products1);
            }
        });
    }
}
