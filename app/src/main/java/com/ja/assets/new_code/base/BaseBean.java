package com.ja.assets.new_code.base;

import java.io.Serializable;

/**
 * Created by long on 17/4/8.
 */

public   class BaseBean<T> implements Serializable {
    public String msg;
    public int code;
    public T data;
}
