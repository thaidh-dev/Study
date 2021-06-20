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
public class TtCaNhanSuaController {
    @Inject
    TtCaNhanService ttCaNhanService;

    @PostMapping(value = "/", params = "btnHuySua")
    public String huy(@ModelAttribute("ttCaNhanSuaDTO") TtCaNhanDTO ttCaNhanSuaDTO,
                      BindingResult br,
                      Model model,
                      @RequestParam("trangDangChon") Integer trangDuocChon,
                      @PageableDefault Pageable pageable) {
        // lấy lại tỉnh huyển xa thôn mình đã chọn trước lúc bấm sửa và tạo ra cái model attribute để truyền về trang ttCaNhan.jsp
        TtCaNhanDTO ttCaNhanDTO = new TtCaNhanDTO();
        BeanUtils.copyProperties(ttCaNhanSuaDTO, ttCaNhanDTO);
        model.addAttribute("ttCaNhanDTO", ttCaNhanDTO);

        // quay về trang lúc mình bấm sửa
        FunctionTtCaNhanController.loadLaiTrang(model, false, ttCaNhanService, true, ttCaNhanDTO, FunctionTtCaNhanController.pageable(pageable, trangDuocChon));
        return "ttCaNhan/ttCaNhan";
    }

    @PostMapping(value = "/", params = "btnCapNhat")
    public String capNhat(@Validated @ModelAttribute("ttCaNhanSuaDTO") TtCaNhanDTO ttCaNhanSuaDTO,
                          BindingResult br,
                          Model model,
                          @RequestParam("trangDangChon") Integer trangDuocChon,
                          @PageableDefault Pageable pageable) {
        // validate
        TtCaNhan ttCaNhan = FunctionTtCaNhanController.kiemTra(ttCaNhanSuaDTO, br, model, ttCaNhanService, true);
        if (ttCaNhan == null) {
            model.addAttribute("page", FunctionTtCaNhanController.pageable(pageable, trangDuocChon));
            FunctionTtCaNhanController.loadLaiTrang(model, false, ttCaNhanService, false, ttCaNhanSuaDTO, null);
            return "ttCaNhan/ttCaNhanSua";
        }

        // sửa 1 cá nhân vào csdl và load lại các combobox, bảng
        ttCaNhanService.update(ttCaNhan);

        huy(ttCaNhanSuaDTO, br, model, trangDuocChon, pageable);
        return "ttCaNhan/ttCaNhan";
    }
}
