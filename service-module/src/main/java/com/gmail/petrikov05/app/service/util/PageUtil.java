package com.gmail.petrikov05.app.service.util;

import static com.gmail.petrikov05.app.service.constant.PageConstant.NUMBER_BY_PAGE;

public class PageUtil {

    public static Integer getStarterPosition(Integer page) {
        return (page - 1) * NUMBER_BY_PAGE;
    }

}
