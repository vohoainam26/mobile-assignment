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
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication22.databinding.FragmentMessageBinding;

/**
 * MessageFragment – Fragment hiển thị thông điệp.
 * 
 * Chức năng:
 * - Nhận dữ liệu từ WelcomeFragment thông qua Safe Args
 * - Hiển thị thông điệp tùy chỉnh
 * - Cho phép quay lại WelcomeFragment
 * 
 * Safe Args:
 * - Sử dụng arguments được định nghĩa trong nav_graph.xml
 * - Truy cập an toàn thông qua generated code sau khi build
 */
public class MessageFragment extends Fragment {

    private static final String TAG = "MessageFragment";
    private FragmentMessageBinding binding;

    public MessageFragment() {
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
        binding = FragmentMessageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: View đã được tạo, thiết lập UI");

        // 🔐 Safe Args: Lấy arguments từ Navigation
        // Sau khi build project, Safe Args sẽ generate class:
        // MessageFragmentArgs.fromBundle(getArguments())
        Bundle args = getArguments();
        String userName = "Người dùng";
        String message = "Ứng dụng tôi là số 1";

        if (args != null) {
            // Lấy dữ liệu từ arguments (Safe Args sẽ generate code type-safe)
            userName = args.getString("userName", userName);
            message = args.getString("message", message);
            Log.d(TAG, "Received arguments - userName: " + userName + ", message: " + message);
        }

        // Hiển thị nội dung với dữ liệu từ arguments
        String displayText = "Xin chào " + userName + "!\n" + message;
        binding.tvMessage.setText(displayText);

        // Thiết lập sự kiện click cho nút quay lại
        binding.btnBack.setOnClickListener(v -> {
            Log.d(TAG, "Button clicked: Quay lại WelcomeFragment");
            
            // Sử dụng Navigation Component để quay lại
            // popUpTo và popUpToInclusive trong nav_graph sẽ xóa MessageFragment khỏi back stack
            Navigation.findNavController(v)
                    .navigate(R.id.action_messageFragment_to_welcomeFragment);
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
