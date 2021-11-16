package dft.app.welcome.ttCaNhanController;

import dft.app.welcome.ttCaNhanController.functionTtCaNhanController.FunctionTtCaNhanController;
import dft.domain.dto.ttCaNhanDTO.TtCaNhanDTO;
import dft.domain.model.*;
import dft.domain.service.TtCaNhanService.TtCaNhanService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;

@Controller
@RequestMapping("/ttCaNhan")
public class TtCaNhanController {
    @Inject
    TtCaNhanService ttCaNhanService;

    @GetMapping(value = "/")
    public String showTrangThongTinCaNhan(Model model) {
        FunctionTtCaNhanController.loadLaiTrang(model, true, ttCaNhanService, false, new TtCaNhanDTO(), null);
        return "ttCaNhan/ttCaNhan";
    }

    @RequestMapping(value = "/", params = "btnHienThi", method = {RequestMethod.GET, RequestMethod.POST})
    public String hienThi(@Validated @ModelAttribute("ttCaNhanDTO") TtCaNhanDTO ttCaNhanDTO, BindingResult br, Model model, @PageableDefault Pageable pageable) {
        if (br.hasFieldErrors("ttHuyen") || br.hasFieldErrors("ttXa") || br.hasFieldErrors("ttThonXom")) {
            FunctionTtCaNhanController.loadLaiTrang(model, false, ttCaNhanService, false, ttCaNhanDTO, null);
            return "ttCaNhan/ttCaNhan";
        }

        FunctionTtCaNhanController.loadLaiTrang(model, false, ttCaNhanService, true, ttCaNhanDTO, pageable);
        return "ttCaNhan/ttCaNhan";
    }

    //<editor-fold defaultstate="collapsed" desc="thêm, sửa, xóa">
    @PostMapping(value = "/", params = "btnThem")
    public String them(Model model) {
        FunctionTtCaNhanController.loadLaiTrang(model, true, ttCaNhanService, false, new TtCaNhanDTO(), null);
        return "ttCaNhan/ttCaNhanThem";
    }

    @PostMapping(value = "/", params = "btnSua")
    public String sua(@RequestParam("txtId") Long id,
                      Model model,
                      @RequestParam("trangDangChon") Integer trangDuocChon,
                      @PageableDefault Pageable pageable) {
        // lấy ra tt cá nhân được chọn sửa
        // chuyển entity thành dto
        TtCaNhan ttCaNhan = ttCaNhanService.findOneById(id);
        TtCaNhanDTO ttCaNhanDTO = new TtCaNhanDTO();
        BeanUtils.copyProperties(ttCaNhan, ttCaNhanDTO, "ngaySinh");
        ttCaNhanDTO.setNgaySinh(FunctionTtCaNhanController.chuyenNgaySinhThanhString(ttCaNhan.getNgaySinh()));

        // đưa thông tin của cá nhân muốn sửa về trang ttCaNhanSua.jsp
        model.addAttribute("ttCaNhanSuaDTO", ttCaNhanDTO);

        // đưa trang đang chọn về trang ttCaNhanSua.jsp
        model.addAttribute("page", FunctionTtCaNhanController.pageable(pageable, trangDuocChon));

        // load trang sửa thông tin cá nhân
        // không load lại danh sách thông tin cá nhân và ttCaNhanDTO
        FunctionTtCaNhanController.loadLaiTrang(model, true, ttCaNhanService, false, ttCaNhanDTO, null);

        return "ttCaNhan/ttCaNhanSua";
    }

    @PostMapping(value = "/", params = "btnXoa")
    public String xoa(@ModelAttribute("ttCaNhanDTO") TtCaNhanDTO ttCaNhanXoaDTO,
                      Model model,
                      @RequestParam("txtId") Long id,
                      @RequestParam("trangDangChon") Integer trangDuocChon,
                      @PageableDefault Pageable pageable) {
//        ttCaNhanService.delete(id);

        // vẫn dữ nguyên các tỉnh huyện xã thôn đc chọn ở combobox sau khi xóa
        TtCaNhanDTO ttCaNhanDTO = new TtCaNhanDTO();
        BeanUtils.copyProperties(ttCaNhanXoaDTO, ttCaNhanDTO);
        model.addAttribute("ttCaNhanDTO", ttCaNhanDTO);

        FunctionTtCaNhanController.loadLaiTrang(model, false, ttCaNhanService, true, ttCaNhanXoaDTO, FunctionTtCaNhanController.pageable(pageable, trangDuocChon));
        return "ttCaNhan/ttCaNhan";
    }
    //</editor-fold>
}
