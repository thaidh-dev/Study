package dft.app.welcome.ttCaNhanController;

import dft.domain.model.DmQuanHuyen;
import dft.domain.model.DmThonXom;
import dft.domain.model.DmXaPhuong;
import dft.domain.service.TtCaNhanService.TtCaNhanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/ttCaNhan")
public class AjaxController {
    @Inject
    TtCaNhanService ttCaNhanService;

    @GetMapping(value = "/ajax/quanHuyens")
    public @ResponseBody List<DmQuanHuyen> quanHuyens (@RequestParam("idTinh") Long idTinh) {
        // lấy mã tỉnh vì tỉnh 1-n huyện và nối vs nhau bằng mã chứ không phải id
        String maTinh = ttCaNhanService.findMaTinh(idTinh);
        return ttCaNhanService.findAllQuanHuyen(maTinh);
    }

    @GetMapping(value = "/ajax/xaPhuongs")
    public @ResponseBody List<DmXaPhuong> xaPhuongs (@RequestParam("idQuanHuyen") Long idQuanHuyen) {
        String maQuanHuyen = ttCaNhanService.findMaQuanHuyen(idQuanHuyen);
        return ttCaNhanService.findAllXaPhuong(maQuanHuyen);
    }

    @GetMapping(value = "/ajax/thonXoms")
    public @ResponseBody List<DmThonXom> thonXoms (@RequestParam("idXaPhuong") Long idXaPhuong) {
        return ttCaNhanService.findAllThonXom(String.valueOf(idXaPhuong));
    }
}
