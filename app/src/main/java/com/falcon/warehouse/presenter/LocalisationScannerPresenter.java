package com.falcon.warehouse.presenter;

import com.falcon.warehouse.contract.BasePresenterImpl;
import com.falcon.warehouse.contract.ILocalisationScannerContract;

import java.math.BigDecimal;

public class LocalisationScannerPresenter extends BasePresenterImpl<ILocalisationScannerContract.View>
        implements ILocalisationScannerContract.Presenter  {

    private final ILocalisationScannerContract.Model model;

    public LocalisationScannerPresenter(ILocalisationScannerContract.Model model) {
        this.model = model;
    }

    @Override
    public void fetchLocalisationByIndex() {
        final String localisationIndex = view.getLocalisationIndex();
        model.updateLocalisationByIndex(localisationIndex);
    }

    @Override
    public void addProductToLocalisation(String productIndex, BigDecimal quantity) {
        model.addProductToLocalisation(view.getLocalisationIndex(), productIndex, quantity);
    }
}
