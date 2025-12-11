package com.example.myapplication22;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myapplication22.databinding.FragmentWelcomeBinding;

/**
 * WelcomeFragment – Fragment đầu tiên trong ứng dụng.
 * 
 * Chức năng:
 * - Hiển thị màn hình chào mừng
 * - Cho phép người dùng điều hướng sang MessageFragment
 * - Sử dụng Navigation Component để điều hướng
 * 
 * Lifecycle của Fragment:
 * - onCreate() -> onCreateView() -> onViewCreated() -> onStart() -> onResume()
 * - onPause() -> onStop() -> onDestroyView() -> onDestroy()
 */
public class WelcomeFragment extends Fragment {

    private static final String TAG = "WelcomeFragment";
    private FragmentWelcomeBinding binding;

    public WelcomeFragment() {
        // Fragment phải có constructor rỗng
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Fragment được tạo");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Tạo view cho Fragment");
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: View đã được tạo, thiết lập UI");

        // Thiết lập sự kiện click cho nút điều hướng
        binding.btnStart.setOnClickListener(v -> {
            Log.d(TAG, "Button clicked: Điều hướng sang MessageFragment");
            
            // 🔐 Safe Args: Truyền dữ liệu an toàn giữa các Fragment
            // Sau khi build project, Safe Args sẽ generate class:
            // WelcomeFragmentDirections.ActionWelcomeFragmentToMessageFragment action = 
            //     WelcomeFragmentDirections.actionWelcomeFragmentToMessageFragment("Tên người dùng", "Thông điệp");
            // Navigation.findNavController(v).navigate(action);
            
            // Hiện tại sử dụng Bundle để truyền arguments
            Bundle args = new Bundle();
            args.putString("userName", "Người dùng Android");
            args.putString("message", "Chào mừng bạn đến với Navigation Component!");
            
            Navigation.findNavController(v)
                    .navigate(R.id.action_welcomeFragment_to_messageFragment, args);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Fragment bắt đầu hiển thị");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Fragment đã sẵn sàng tương tác với người dùng");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: Fragment tạm dừng (người dùng không còn thấy)");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: Fragment dừng hoàn toàn");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: View bị hủy, giải phóng binding");
        binding = null; // Tránh memory leak
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Fragment bị hủy");
    }
}
