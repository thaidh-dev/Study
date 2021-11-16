package dft.app.welcome.ttCaNhanController;

import dft.app.welcome.ttCaNhanController.functionTtCaNhanController.FunctionTtCaNhanController;
import dft.domain.dto.ttCaNhanDTO.TtCaNhanDTO;
import dft.domain.model.TtCaNhan;
import dft.domain.service.TtCaNhanService.TtCaNhanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@RequestMapping("/ttCaNhan")
public class TtCaNhanThemController {
    @Inject
    TtCaNhanService ttCaNhanService;

    @PostMapping(value = "/", params = "btnLuu")
    public String luu(@Validated @ModelAttribute("ttCaNhanDTO") TtCaNhanDTO ttCaNhanDTO, BindingResult br, Model model) {
        TtCaNhan ttCaNhan = FunctionTtCaNhanController.kiemTra(ttCaNhanDTO, br, model, ttCaNhanService, false);
        if (ttCaNhan == null) {
            FunctionTtCaNhanController.loadLaiTrang(model, false, ttCaNhanService, false, ttCaNhanDTO, null);
            return "ttCaNhan/ttCaNhanThem";
        }

        // thêm 1 cá nhân vào csdl và load lại các combobox, bảng
        ttCaNhanService.insert(ttCaNhan);
        FunctionTtCaNhanController.loadLaiTrang(model, true, ttCaNhanService, false, new TtCaNhanDTO(), null);
        return "ttCaNhan/ttCaNhanThem";
    }

    @PostMapping(value = "/", params = "btnHuyThem")
    public String huy(Model model) {
        FunctionTtCaNhanController.loadLaiTrang(model, true, ttCaNhanService, false, new TtCaNhanDTO(), null);
        return "ttCaNhan/ttCaNhan";
    }
}
