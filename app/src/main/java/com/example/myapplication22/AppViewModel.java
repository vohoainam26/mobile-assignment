package com.example.myapplication22;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * AppViewModel – ViewModel để quản lý state của ứng dụng.
 * 
 * Chức năng:
 * - Lưu trữ và quản lý dữ liệu của ứng dụng
 * - Dữ liệu được giữ lại khi Fragment bị recreate (ví dụ: xoay màn hình)
 * - Cung cấp LiveData để Fragment có thể observe và cập nhật UI
 * 
 * Lifecycle:
 * - ViewModel sống lâu hơn Fragment
 * - Tự động bị hủy khi Activity/Fragment bị destroy hoàn toàn
 */
public class AppViewModel extends ViewModel {

    private static final String TAG = "AppViewModel";
    
    // LiveData để lưu trữ tên người dùng
    private final MutableLiveData<String> userName = new MutableLiveData<>("Người dùng");
    
    // LiveData để lưu trữ thông điệp
    private final MutableLiveData<String> message = new MutableLiveData<>("Ứng dụng tôi là số 1");

    /**
     * Lấy tên người dùng hiện tại
     */
    public LiveData<String> getUserName() {
        return userName;
    }

    /**
     * Cập nhật tên người dùng
     */
    public void setUserName(String name) {
        Log.d(TAG, "setUserName: " + name);
        userName.setValue(name);
    }

    /**
     * Lấy thông điệp hiện tại
     */
    public LiveData<String> getMessage() {
        return message;
    }

    /**
     * Cập nhật thông điệp
     */
    public void setMessage(String msg) {
        Log.d(TAG, "setMessage: " + msg);
        message.setValue(msg);
    }

    /**
     * Được gọi khi ViewModel bị hủy
     * Nơi để cleanup resources nếu cần
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ViewModel bị hủy");
    }
}

