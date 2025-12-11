package com.example.myapplication22;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication22.databinding.ActivityMainBinding;

/**
 * MainActivity – Activity chính của ứng dụng.
 * 
 * Chức năng:
 * - Chứa NavHostFragment để quản lý navigation giữa các Fragment
 * - Thiết lập NavController để điều khiển navigation
 * - Quản lý lifecycle của Navigation Component
 * 
 * Navigation Component:
 * - NavHostFragment: Container chứa các Fragment
 * - NavController: Điều khiển navigation flow
 * - Navigation Graph: Định nghĩa các destination và actions
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Activity được tạo");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 🧭 Thiết lập Navigation Controller
        setupNavigation();
    }

    /**
     * Thiết lập Navigation Component và NavController
     */
    private void setupNavigation() {
        // Lấy NavHostFragment từ layout
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            // Lấy NavController từ NavHostFragment
            navController = navHostFragment.getNavController();
            Log.d(TAG, "NavController đã được thiết lập");

            // Cấu hình AppBar (nếu cần sử dụng Toolbar/ActionBar)
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    navController.getGraph()
            ).build();

            // Kết nối NavigationUI với ActionBar (nếu có)
            // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        } else {
            Log.e(TAG, "NavHostFragment không tìm thấy!");
        }
    }

    /**
     * Xử lý nút Back để hỗ trợ Navigation Component
     * Nếu NavController có thể xử lý back navigation, nó sẽ xử lý
     * Nếu không, Activity sẽ xử lý (thoát app)
     */
    @Override
    public boolean onSupportNavigateUp() {
        if (navController != null) {
            return navController.navigateUp() || super.onSupportNavigateUp();
        }
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Activity bắt đầu hiển thị");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Activity đã sẵn sàng tương tác");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: Activity tạm dừng");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: Activity dừng");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Activity bị hủy");
        binding = null;
    }
}
