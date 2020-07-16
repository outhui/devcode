package com.basecode.binding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.basecode.glide.GlideLoader;

/**
 * databinding
 */
public class ViewAdapter {

    //--------ImageView--------//
    @BindingAdapter(value = {"imageUrl", "placeholderRes", "circle", "radius"}, requireAll = false)
    public static void load(ImageView imageView, String url, Drawable placeholderRes, boolean circle, int radius) {
        GlideLoader.load(imageView, placeholderRes, url, circle, radius);
    }

    //-----View------//
    @BindingAdapter(value = "onClickCommand", requireAll = false)
    public static void setOnClickListener(final View view, final BindingCommand<View> bindingCommand) {
        view.setClickable(true);
        view.setFocusable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingCommand.execute(v);
            }
        });
    }

    //------Edittext----//
    @BindingAdapter(value = {"requestFocus"}, requireAll = false)
    public static void requestFocusCommand(EditText editText, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
        editText.setFocusableInTouchMode(needRequestFocus);
    }

    //----Edittext-----//
    @BindingAdapter(value = {"textChanged"}, requireAll = false)
    public static void addTextChangedListener(EditText editText, final BindingCommand<String> textChanged) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                if (textChanged != null) {
                    textChanged.execute(text.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
