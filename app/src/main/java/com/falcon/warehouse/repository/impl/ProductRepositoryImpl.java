package com.falcon.warehouse.repository.impl;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.falcon.warehouse.dao.ProductDao;
import com.falcon.warehouse.entity.Product;
import com.falcon.warehouse.repository.ProductRepository;
import com.falcon.warehouse.service.ProductService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositoryImpl extends BaseRepositoryImpl implements ProductRepository {

    private final ProductDao productDao;
    private final ProductService productService;
    private final Executor executor;

    @Inject
    public ProductRepositoryImpl(ProductDao productDao, ProductService productService, Executor executor) {
        this.productDao = productDao;
        this.productService = productService;
        this.executor = executor;
    }

    /**
     * Saves only locally
     *
     * @param product product for save in Room db
     */
    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    /**
     * Saves new product
     * If executor throw error after api save call, try to save locally only
     *
     * @param product new product for save
     */
    @Override
    public void saveNewProduct(Product product) {
        try {
            apiSaveProduct(product);
        } catch (RuntimeException ex) {
            Log.e("Catching Response", ex.getLocalizedMessage());
            productDao.saveProduct(product);
        }
    }

    private void apiSaveProduct(Product product) {
        executor.execute(() -> productService.saveProduct(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                executor.execute(() -> {
                    Product savedProduct = response.body();

                    if (savedProduct == null) {
                        throw new RuntimeException("Save returns empty object");
                    } else {
                        savedProduct.setLastFetchedDate(new Date());
                        productDao.saveProduct(product);
                    }
                });
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
                throw new RuntimeException("Error in save response");
            }
        }));
    }

    @Override
    public LiveData<Product> getProductByIndex(String productIndex) {
        if (productIndex.isEmpty()) {
            return productDao.getLastFetchedProduct();
        } else {
            fetchProductByIndex(productIndex);
            return productDao.getProductByIndex(productIndex);
        }
    }

    private void fetchProductByIndex(final String productIndex) {
        executor.execute(() -> {
            boolean isRefreshTime = (productDao.fetchedProduct(productIndex, getMaxRefreshTime(new Date())) == null);

            if (isRefreshTime) {
                productService.getProductByIndex(productIndex).enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        executor.execute(() ->{
                            Product product = response.body();
                            if (product != null) {
                                product.setLastFetchedDate(new Date());
                                productDao.saveProduct(product);
                            } else {
                                Log.e("NULL_OBJECT", "Product is null");
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                        Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
                    }
                });
            }
        });
    }

    /**
     * Method checks if data was fetched. Is only for executor call
     *
     * @param productIndex index of product
     * @param lastRefreshMax Date of last refresh -3 minutes
     * @return product if exist else returns null
     */
    @Override
    public Product fetchedProduct(String productIndex, Date lastRefreshMax) {
        return productDao.fetchedProduct(productIndex, lastRefreshMax);
    }

    @Override
    public LiveData<Product> getLastFetchedProduct() {
        return productDao.getLastFetchedProduct();
    }

    @Override
    public LiveData<List<Product>> getAll() {
        fetchAllProducts();
        return productDao.getAll();
    }

    /**
     * Update Product
     * If executor throw error after api update call, try to save locally only
     *
     * @param product product for update
     */
    @Override
    public void updateProduct(Product product) {
        try {
            apiUpdateProduct(product);
        } catch (RuntimeException ex) {
            Log.e("Catching Response", ex.getLocalizedMessage());
            productDao.updateProduct(product);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        try {
            apiDelete(product);
        } catch (RuntimeException ex) {
            Log.e("Catching Response", ex.getLocalizedMessage());
            productDao.deleteProduct(product);
        }
    }

    @Override
    public void deleteAll() {
        productDao.deleteAll();
    }

    private void apiDelete(Product product) {
        executor.execute(() -> productService.delteProduct(product).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                executor.execute(() -> productDao.deleteProduct(product));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
                throw new RuntimeException("Error in delete response");
            }
        }));
    }

    private void apiUpdateProduct(Product product) {
        executor.execute(() -> productService.updateProduct(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
               executor.execute(() -> {
                   Product savedProduct = response.body();

                   if (savedProduct == null) {
                       throw new RuntimeException("Save returns empty object");
                   } else {
                       savedProduct.setLastFetchedDate(new Date());
                       productDao.updateProduct(product);
                   }
               });

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
                throw new RuntimeException("Error in save response");
            }
        }));
    }

    private void fetchAllProducts() {
        executor.execute(() -> productService.geAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                executor.execute(() -> {
                    List<Product> products = response.body();

                    if (products != null) {
                        //cleaning local table
                        deleteAll();
                        products.forEach(product -> {
                            product.setLastFetchedDate(new Date());
                            productDao.saveProduct(product);
                        });
                    }else {
                        Log.e("NULL_OBJECT", "Product is null");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("ERROR_IN_FETCH", call.toString() + t.getLocalizedMessage());
                Log.e("SKIPPING_DATA_UPDATE", "Fetching data locally");
            }
        }));
    }

}
