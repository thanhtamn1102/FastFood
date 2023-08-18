package com.example.demo.model;

import java.io.Serializable;

public enum DonHangStatus implements Serializable {

    TAO_MOI_CHO_XAC_NHAN,
    DANG_XU_LY,
    DANG_GIAO_HANG,
    DA_GIAO,
    DA_HOAN_THANH,
    DA_HUY;

    @Override
    public String toString() {
        switch (this) {
            case TAO_MOI_CHO_XAC_NHAN:
                return "Tạo mới chờ xác nhận";
            case DANG_XU_LY:
                return "Đang xử lý";
            case DANG_GIAO_HANG:
                return "Đang giao hàng";
            case DA_GIAO:
                return "Đã giao";
            case DA_HOAN_THANH:
                return "Đã hoàn thành";
            case DA_HUY:
                return "Đã hủy";
        }
        return super.toString();
    }
}
