package com.highlands.tianFuFinance.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.highlands.common.util.StringUtil;
import com.highlands.tianFuFinance.R;
import com.highlands.tianFuFinance.databinding.LoginViewBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * @author xuliangliang
 * @date 2020/11/2
 * copyright(c) Highlands
 */
public class LoginEditView extends ConstraintLayout {
    private LoginViewBinding binding;

    public LoginEditView(@NonNull Context context) {
        super(context);
        init(context, null);
    }


    public LoginEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LoginEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public LoginEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.login_view, this, true);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.LoginEditView);
        if (attributes != null) {
            String title = attributes.getString(R.styleable.LoginEditView_title_text);
            binding.title.setText(StringUtil.emptyIs(title));
            String editHint = attributes.getString(R.styleable.LoginEditView_edit_hint);
            binding.edit.setHint(StringUtil.emptyIs(editHint));
            String editText = attributes.getString(R.styleable.LoginEditView_edit_text);
            if(!StringUtil.isStringNull(editText)){
            binding.edit.setText(editText);
            }
            int maxLength = attributes.getInt(R.styleable.LoginEditView_max_length,11);
            binding.edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});

            attributes.recycle();
        }
    }

    public String getText() {
        return binding.edit.getText().toString().trim();
    }

    public void setInputType(int inputType) {
        binding.edit.setInputType(inputType);
    }
}
