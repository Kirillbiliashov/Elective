package com.example.elective.utils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.Constants.SIZE_PARAM;

@Component
public class PaginationUtils {

  public void setPaginationAttributes(Model model, Page<?> pageInfo) {
    model.addAttribute(IS_LAST_ATTR, pageInfo.isLast());
    model.addAttribute(IS_FIRST_ATTR, pageInfo.isFirst());
    model.addAttribute(PAGE_PARAM, pageInfo.getNumber());
    model.addAttribute(SIZE_PARAM, pageInfo.getTotalElements());
  }

}
