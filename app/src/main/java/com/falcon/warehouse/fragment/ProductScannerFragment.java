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
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.Result;

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
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView(this);
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("tag", rawResult.getText()); // Prints scan results
        Log.v("tag", rawResult.getBarcodeFormat().toString());

        setProductIndex(rawResult.getText());
        presenter.fetchProductByIndex();

        ((NavigationHost) getActivity())
                .navigateTo(new ProductDetailFragment(), false);
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
