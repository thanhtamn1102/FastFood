package com.example.demo.mapper;

import com.example.demo.dto.GioHangItemRequest;
import com.example.demo.model.GioHangItem;
import com.example.demo.model.MonAn;
import com.example.demo.repository.MonAnRepository;
import com.example.demo.service.MonAnService;
import com.example.demo.service_impl.MonAnServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GioHangItemMapper {
    private static MonAnServiceImpl service = new MonAnServiceImpl();

    public static GioHangItem toGioHangItem(GioHangItemRequest gioHangItemRequest) {
        GioHangItem gioHangItem = new GioHangItem();
        gioHangItem.setMonAn(service.findById(gioHangItemRequest.getMaMonAn()));
        gioHangItem.setSoLuong(gioHangItemRequest.getSoLuong());
        gioHangItem.setDsTuyChon(gioHangItemRequest.getDsTuyChon());
        return gioHangItem;
    }

}
