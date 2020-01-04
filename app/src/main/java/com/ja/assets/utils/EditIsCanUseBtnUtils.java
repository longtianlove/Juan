package com.ja.assets.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditIsCanUseBtnUtils {
    private List<EditText> editTextList = new ArrayList<>();
    private Button btn;
    private Context context;
    private String textNull, textFull;
    private Integer colorNull, colorFull;
    private Integer backgroundResourceNull, backgroundResourceFull;

    //实例化
    public static EditIsCanUseBtnUtils getInstance() {
        return new EditIsCanUseBtnUtils();
    }

    //Null时候的颜色
    public EditIsCanUseBtnUtils setNullbackgroundResource(Integer backgroundResourceNull) {
        this.backgroundResourceNull = backgroundResourceNull;
        return this;
    }

    //Full时候的颜色
    public EditIsCanUseBtnUtils setFullbackgroundResource(Integer backgroundResourceFull) {
        this.backgroundResourceFull = backgroundResourceFull;
        return this;
    }

    //Null时候的颜色
    public EditIsCanUseBtnUtils setNullColor(Integer colorNull) {
        this.colorNull = colorNull;
        return this;
    }

    //Full时候的颜色
    public EditIsCanUseBtnUtils setFullColor(Integer colorFull) {
        this.colorFull = colorFull;
        return this;
    }

    //绑定的Button
    public EditIsCanUseBtnUtils addButton(Button btn) {
        this.btn = btn;
        btn.setEnabled(false);
        return this;
    }

    //添加EditText
    public EditIsCanUseBtnUtils addEditext(EditText editText) {
        editTextList.add(editText);
        return this;
    }
    //给Button设置文字
    public EditIsCanUseBtnUtils setText(String text) {
        btn.setText(text);
        return this;
    }

    public EditIsCanUseBtnUtils setTextNull(String textNull) {
        this.textNull = textNull;
        return this;
    }

    public EditIsCanUseBtnUtils setTextFull(String textFull) {
        this.textFull = textFull;
        return this;
    }

    //添加Context对象
    public EditIsCanUseBtnUtils setContext(Context context) {
        this.context = context;
        return this;
    }

    //创建
    public void build() {
        setWatcher();
    }

    private void setWatcher() {
        for (int i = 0; i < editTextList.size(); i++) {
            editTextList.get(i).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 0) {
                        setBtnUnavailable();
                        return;
                    }
                    boolean tag = false;
                    for (int i1 = 0; i1 < editTextList.size(); i1++) {
                        if (editTextList.get(i1).getText().length() != 0) {
                            tag = true;
                        } else {
                            tag = false;
                            break;
                        }
                    }
                    if (tag) {
                        setBtnAvailable();
                    } else {
                        setBtnUnavailable();
                    }
                }
            });
        }
    }

    //Full时
    private void setBtnAvailable() {
        //颜色
        if (colorFull != null) {
            btn.setBackgroundColor(colorFull);
        }
        //文字
        if (!TextUtils.isEmpty(textFull)) {
            btn.setText(textFull);
        }
        //背景
        if (backgroundResourceFull != null) {
            btn.setBackgroundResource(backgroundResourceFull);
        }
        btn.setEnabled(true);
    }

    //Null时
    private void setBtnUnavailable() {
        if (colorNull != null) {
            btn.setBackgroundColor(colorNull);
        }
        if (!TextUtils.isEmpty(textNull)) {
            btn.setText(textNull);
        }
        if (backgroundResourceNull != null) {
            btn.setBackgroundResource(backgroundResourceNull);
        }
        btn.setEnabled(false);
    }
}

