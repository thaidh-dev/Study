package dft.app.welcome;

import dft.domain.dto.dmThonXom.DmThonXomDTO;
import dft.domain.model.DmQuanHuyen;
import dft.domain.model.DmQuanHuyenCriteria;
import dft.domain.model.DmThonXom;
import dft.domain.model.DmXaPhuong;
import dft.domain.service.DmXaPhuongService;
import dft.domain.service.TtCaNhanService.TtCaNhanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/dmxaphuong/")
public class DmXaPhuongController {
    @Inject
    DmXaPhuongService dmXaPhuongService;

    @ModelAttribute("searchCriteriaForm")
    public DmQuanHuyenCriteria searchDmXaPhuongCriteria(){
        return new DmQuanHuyenCriteria();
    }

    @GetMapping(value = "list")
    public String showAllDmXaPhuong(Model model, DmQuanHuyenCriteria criteria, @PageableDefault(size = 5) Pageable pageable) {
        if (!StringUtils.hasLength(criteria.getTen())) {
            criteria.setTen("");
        }
        Page<DmXaPhuong> page = dmXaPhuongService.searchDmXaPhuong(criteria, pageable);
        model.addAttribute("searchCriteriaForm",criteria);
        model.addAttribute("page", page);
//        model.addAttribute("listDmXaPhuong", dmXaPhuongService.findAll());
        return "dmxaphuong/dmxaphuong";
    }

    /*Chức năng: Xóa thôn, xóm theo id*/
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteDmXaPhuongById(@PathVariable("id") int dmXaPhuongId, Model model) {
        dmXaPhuongService.deleteById(dmXaPhuongId);
        return "redirect:/dmxaphuong/list";
    }

    /*Chức năng: Update thôn, xóm theo id*/
    @RequestMapping(value = "edit/{id}")
    public String editDmThonXomById(@PathVariable("id") int dmXaPhuongId, Model model) throws Exception {
        DmXaPhuong dmXaPhuong = dmXaPhuongService.findById(dmXaPhuongId);
        model.addAttribute("dmXaPhuong", dmXaPhuong);
        return "dmxaphuong/dmxaphuongedit";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("dmXaPhuong") DmXaPhuong dmXaPhuong) throws Exception {
        dmXaPhuongService.update(dmXaPhuong);
        return "redirect:/dmxaphuong/list";
    }

}
