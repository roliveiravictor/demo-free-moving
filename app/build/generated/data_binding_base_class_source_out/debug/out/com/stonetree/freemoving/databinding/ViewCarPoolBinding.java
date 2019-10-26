// Generated by data binding compiler. Do not edit!
package com.stonetree.freemoving.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.stonetree.freemoving.R;
import com.stonetree.freemoving.feature.pool.view.CarPoolView;
import com.stonetree.freemoving.feature.pool.viewmodel.CarPoolViewModel;
import com.stonetree.restclient.core.model.NetworkState;
import com.stonetree.view.databinding.ViewCoreBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ViewCarPoolBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView carPool;

  @NonNull
  public final ViewCoreBinding viewCore;

  @Bindable
  protected NetworkState mNetwork;

  @Bindable
  protected CarPoolViewModel mVm;

  @Bindable
  protected CarPoolView mView;

  protected ViewCarPoolBinding(Object _bindingComponent, View _root, int _localFieldCount,
      RecyclerView carPool, ViewCoreBinding viewCore) {
    super(_bindingComponent, _root, _localFieldCount);
    this.carPool = carPool;
    this.viewCore = viewCore;
    setContainedBinding(this.viewCore);
  }

  public abstract void setNetwork(@Nullable NetworkState network);

  @Nullable
  public NetworkState getNetwork() {
    return mNetwork;
  }

  public abstract void setVm(@Nullable CarPoolViewModel vm);

  @Nullable
  public CarPoolViewModel getVm() {
    return mVm;
  }

  public abstract void setView(@Nullable CarPoolView view);

  @Nullable
  public CarPoolView getView() {
    return mView;
  }

  @NonNull
  public static ViewCarPoolBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.view_car_pool, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ViewCarPoolBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ViewCarPoolBinding>inflateInternal(inflater, R.layout.view_car_pool, root, attachToRoot, component);
  }

  @NonNull
  public static ViewCarPoolBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.view_car_pool, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ViewCarPoolBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ViewCarPoolBinding>inflateInternal(inflater, R.layout.view_car_pool, null, false, component);
  }

  public static ViewCarPoolBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ViewCarPoolBinding bind(@NonNull View view, @Nullable Object component) {
    return (ViewCarPoolBinding)bind(component, view, R.layout.view_car_pool);
  }
}
