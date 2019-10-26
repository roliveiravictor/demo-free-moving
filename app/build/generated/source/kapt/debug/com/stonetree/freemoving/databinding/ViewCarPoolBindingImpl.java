package com.stonetree.freemoving.databinding;
import com.stonetree.freemoving.R;
import com.stonetree.freemoving.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ViewCarPoolBindingImpl extends ViewCarPoolBinding implements com.stonetree.freemoving.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(3);
        sIncludes.setIncludes(0, 
            new String[] {"view_core"},
            new int[] {1},
            new int[] {com.stonetree.view.R.layout.view_core});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.car_pool, 2);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ViewCarPoolBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ViewCarPoolBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.recyclerview.widget.RecyclerView) bindings[2]
            , (com.stonetree.view.databinding.ViewCoreBinding) bindings[1]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.stonetree.freemoving.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        viewCore.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (viewCore.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.network == variableId) {
            setNetwork((com.stonetree.restclient.core.model.NetworkState) variable);
        }
        else if (BR.view == variableId) {
            setView((com.stonetree.freemoving.feature.CarPoolView) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setNetwork(@Nullable com.stonetree.restclient.core.model.NetworkState Network) {
        this.mNetwork = Network;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.network);
        super.requestRebind();
    }
    public void setView(@Nullable com.stonetree.freemoving.feature.CarPoolView View) {
        this.mView = View;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.view);
        super.requestRebind();
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        viewCore.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewCore((com.stonetree.view.databinding.ViewCoreBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewCore(com.stonetree.view.databinding.ViewCoreBinding ViewCore, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.stonetree.restclient.core.model.NetworkState network = mNetwork;
        com.stonetree.freemoving.feature.CarPoolView view = mView;
        com.stonetree.restclient.core.model.Status networkStatus = null;
        int networkStatusIsLoading = 0;

        if ((dirtyFlags & 0xaL) != 0) {



                if (network != null) {
                    // read network.status
                    networkStatus = network.getStatus();
                }


                if (networkStatus != null) {
                    // read network.status.isLoading
                    networkStatusIsLoading = networkStatus.isLoading();
                }
        }
        // batch finished
        if ((dirtyFlags & 0xaL) != 0) {
            // api target 1

            this.viewCore.setNetwork(networkStatusIsLoading);
        }
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.viewCore.setRetry(mCallback1);
        }
        executeBindingsOn(viewCore);
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // view
        com.stonetree.freemoving.feature.CarPoolView view = mView;
        // view != null
        boolean viewJavaLangObjectNull = false;



        viewJavaLangObjectNull = (view) != (null);
        if (viewJavaLangObjectNull) {


            view.onRequestRetry();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewCore
        flag 1 (0x2L): network
        flag 2 (0x3L): view
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}