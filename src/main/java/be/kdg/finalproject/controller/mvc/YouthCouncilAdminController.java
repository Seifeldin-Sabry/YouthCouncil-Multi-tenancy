package be.kdg.finalproject.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/youth-council-admin/")
public class YouthCouncilAdminController {

    @GetMapping("login")
    public ModelAndView showYCAdminLoginPage() {
        var mav = new ModelAndView();
        mav.setViewName("yc_admin_login");
        return mav;
    }

    @GetMapping("home")
    public ModelAndView showYCAdminHomePage() {
        var mav = new ModelAndView();
        mav.setViewName("yc_admin_home");
        return mav;
    }

    @GetMapping("add-news")
    public ModelAndView showYCAdminAddNewsPage() {
        var mav = new ModelAndView();
        mav.setViewName("yc_admin_add_news");
        return mav;
    }

    @GetMapping("news-items")
    public ModelAndView showYCAdminNewItemsPage() {
        var mav = new ModelAndView();
        mav.setViewName("yc_admin_newsItems");
        return mav;
    }

    @GetMapping("statistics")
    public ModelAndView showYCAdminStatisticsPage() {
        var mav = new ModelAndView();
        mav.setViewName("yc_admin_statistics");
        return mav;
    }

    @GetMapping("action-points")
    public ModelAndView showYCAdminActionPointsPage() {
        var mav = new ModelAndView();
        mav.setViewName("yc_admin_actionpoints");
        return mav;
    }

    @GetMapping("ideas")
    public ModelAndView showYCAdminIdeasPage() {
        var mav = new ModelAndView();
        mav.setViewName("yc_admin_ideas");
        return mav;
    }

    @GetMapping("create-moderator")
    public ModelAndView showYCAdminCreateModeratorPage() {
        var mav = new ModelAndView();
        mav.setViewName("yc_admin_create_moderator");
        return mav;
    }

    @GetMapping("manage-users")
    public ModelAndView showYCAdminManageUsersPage() {
        var mav = new ModelAndView();
        mav.setViewName("yc_admin_manage_users");
        return mav;
    }
}
