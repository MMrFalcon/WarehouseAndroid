package com.falcon.warehouse.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.falcon.warehouse.NavigationHost;
import com.falcon.warehouse.R;
import com.falcon.warehouse.contract.IProductScannerContract;
import com.falcon.warehouse.root.App;
import com.falcon.warehouse.root.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.Result;

import java.math.BigDecimal;
import java.util.Objects;

import javax.inject.Inject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ProductScannerFragment extends Fragment implements ZXingScannerView.ResultHandler,
        IProductScannerContract.View {

    private ZXingScannerView mScannerView;
    private TextInputEditText scanOutput;

    private final int CAMERA_PERMISSION_STATE = 1;

    @Inject
    IProductScannerContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) this.getActivity().getApplication()).getComponent().inject(this);
        View fragmentView = inflater.inflate(R.layout.scanner_fragment, container, false);

        presenter.attachView(this);

        scanOutput = fragmentView.findViewById(R.id.scanOutput);

        final LinearLayout scanCamera = fragmentView.findViewById(R.id.scanCamera);

        checkCameraPermission(Objects.requireNonNull(getActivity()));
        mScannerView = new ZXingScannerView(getContext());   // Programmatically initialize the scanner fragmentView
        scanCamera.addView(mScannerView);

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        mScannerView.setResultHandler(null);
        mScannerView.stopCameraPreview(); //stopPreview
        mScannerView.stopCamera();
        mScannerView.destroyDrawingCache();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mScannerView.setResultHandler(null);
        mScannerView.stopCameraPreview(); //stopPreview
        mScannerView.stopCamera();
        mScannerView.destroyDrawingCache();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        mScannerView.setResultHandler(null);
        mScannerView.stopCameraPreview(); //stopPreview
        mScannerView.stopCamera();
        mScannerView.destroyDrawingCache();
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        mScannerView.setResultHandler(null);
        mScannerView.stopCameraPreview(); //stopPreview
        mScannerView.stopCamera();
        mScannerView.destroyDrawingCache();
        super.onStop();
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("tag", rawResult.getText()); // Prints scan results
        Log.v("tag", rawResult.getBarcodeFormat().toString());

        setProductIndex(rawResult.getText());

        Bundle requestBundle = this.getArguments();
        if (requestBundle != null) {
            if (requestBundle.getString(Constants.SCAN_TYPE_KEY).equals(Constants.ADD_PRODUCT_TO_LOCALISATION)) {
                BigDecimal quantity = new BigDecimal(requestBundle.getString(Constants.QUANTITY));
                String localisationIndex = requestBundle.getString(Constants.PROD_LOCALISATION_INDEX_KEY);
                presenter.addLocalisationToProduct(localisationIndex, quantity);

                ProductLocalisationListFragment productLocalisationListFragment = new ProductLocalisationListFragment();

                ((NavigationHost) getActivity())
                        .navigateTo(productLocalisationListFragment, false);

            } else {
                presenter.fetchProductByIndex();

                Bundle bundle = new Bundle();
                bundle.putString(Constants.SCAN_PRODUCT_KEY, rawResult.getText());

                ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                productDetailFragment.setArguments(bundle);

                ((NavigationHost) getActivity())
                        .navigateTo(productDetailFragment, false);
            }
        } else {
            presenter.fetchProductByIndex();

            Bundle bundle = new Bundle();
            bundle.putString(Constants.SCAN_PRODUCT_KEY, rawResult.getText());

            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            productDetailFragment.setArguments(bundle);

            ((NavigationHost) getActivity())
                    .navigateTo(productDetailFragment, false);
        }



    }

    private void checkCameraPermission(Activity activity) {

        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PERMISSION_STATE);
        }
    }

    @Override
    public String getProductIndex() {
        if (scanOutput.getText() != null) {
            return scanOutput.getText().toString();
        } else {
            return  "";
        }
    }

    @Override
    public void setProductIndex(String productIndex) {
        scanOutput.setText(productIndex);
    }
}
