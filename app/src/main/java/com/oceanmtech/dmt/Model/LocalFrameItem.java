package com.oceanmtech.dmt.Model;

import com.oceanmtech.dmt.R;

import java.io.Serializable;

public class LocalFrameItem implements Serializable {
    public int layout_id = R.layout.custom_frame_1;
    public int preview_id = R.layout.custom_frame_preview_1;

    public int getLayout_id() {
        return this.layout_id;
    }

    public void setLayout_id(int i) {
        this.layout_id = i;
    }

    public LocalFrameItem(int i, int i2) {
        this.layout_id = i;
        this.preview_id = i2;
    }

    public int getPreview_id() {
        return this.preview_id;
    }

    public void setPreview_id(int i) {
        this.preview_id = i;
    }
}
