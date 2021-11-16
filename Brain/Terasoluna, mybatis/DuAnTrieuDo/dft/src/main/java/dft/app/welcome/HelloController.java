package dft.app.welcome;

import dft.domain.model.DmQuanHuyen;
import dft.domain.model.DmThonXom;
import dft.domain.model.DmTinhTP;
import dft.domain.model.DmXaPhuong;
import dft.domain.model.TtCaNhan;
import dft.domain.service.DmQuanHuyenService;
import dft.domain.service.DmThonXomService;
import dft.domain.service.DmTinhTPService;
import dft.domain.service.DmXaPhuongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HelloController {

    private static final Logger logger = LoggerFactory
            .getLogger(HelloController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */

    @Inject
    DmTinhTPService dmTinhTPService;

    @Inject
    DmQuanHuyenService dmQuanHuyenService;

    @Inject
    DmXaPhuongService dmXaPhuongService;

    @Inject
    DmThonXomService dmThonXomService;

    // Lấy danh sách Tỉnh lên Seclect
    @ModelAttribute("litsTinhTP_Selects")
    public List<DmTinhTP> listDmTinhTP() {
        return dmTinhTPService.findAll();
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model, @ModelAttribute("TtCaNhan") TtCaNhan ttCaNhan) {
        model.addAttribute("TtCaNhan", new TtCaNhan());
        logger.info("Welcome home! The client locale is {}.", locale);
        return "welcome/home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(Locale locale, Model model, @ModelAttribute("TtCaNhan") TtCaNhan TtCaNhan) {
        return "welcome/home";
    }

    @RequestMapping(value="/ajax/QuanHuyen")
    public @ResponseBody List<DmQuanHuyen> sectionList(@RequestParam(value="tinhMa", required=true) String MaTinh){
        return dmQuanHuyenService.findByMaTinh(MaTinh);
    }

    @RequestMapping(value="/ajax/PhuongXa")
    public @ResponseBody List<DmXaPhuong> sectionListPhuongXa(@RequestParam(value="quanHuyenMa", required=true) String maQuanHuyen){
        return dmXaPhuongService.findByMaQuanHuyen(maQuanHuyen);
    }

    @RequestMapping(value="/ajax/ThonXom")
    public @ResponseBody List<DmThonXom> sectionListThonXom(@RequestParam(value="phuongXaMa", required=true) Integer MaXaPhuong){
        return dmThonXomService.findThonXomByMaXaPhuong(MaXaPhuong);
    }

}
