package com.highlands.tianFuFinance.fun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.highlands.tianFuFinance.R;

public class BlankFragment extends AppCompatActivity {

    TextView mTextView;
    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    private BlankViewModel mViewModel;
private StringBuffer a = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_fragment);
        mTextView = findViewById(R.id.mTextView);
        mViewModel = new ViewModelProvider(this).get(BlankViewModel.class);
        // Create the observer which updates the UI.
        final Observer<String> nameObserver = newName -> {
            // Update the UI, in this case, a TextView.
            mTextView.setText(newName);
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.getCurrentName().observe(this, nameObserver);

    }


    public void click(View view) {
        a.append("哈个");
        mViewModel.getCurrentName().setValue(a.toString());
    }
}