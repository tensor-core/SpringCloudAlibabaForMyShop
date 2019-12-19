package com.funtl.myshop.service.reg.controller;

import com.funtl.myshop.commons.domain.TbUser;
import com.funtl.myshop.commons.dto.AbstractBaseResult;
import com.funtl.myshop.commons.dto.BaseResultFactory;
import com.funtl.myshop.commons.service.TbUserService;
import com.funtl.myshop.commons.validator.BeanValidator;
import com.funtl.myshop.commons.web.AbstractBaseController;
import com.funtl.myshop.service.reg.service.RegService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "reg")

public class RegController extends AbstractBaseController<TbUser> {

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private RegService regService;

    @ApiOperation(value = "用户注册", notes = "以实体类为参数，注意用户名和邮箱不要重复")//value是方法是干什么的，notes是说明
    @PostMapping(value = "user")
   public AbstractBaseResult reg(@ApiParam(name = "tbUser", value = "用户模型")TbUser tbUser) {//参数说明

        //数据校验
        String message = BeanValidator.validator(tbUser);

        if (StringUtils.isNoneBlank(message)) {

            return error(message, null);
        }


        // 验证用户名是否重复
        if (!tbUserService.unique("username", tbUser.getUsername())) {

            return error("用户名已存在", null);
        }

        // 验证邮箱是否重复
        if (!tbUserService.unique("email", tbUser.getEmail())) {
            return error("邮箱重复，请重试", null);
        }

        //注册用户
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        TbUser user = tbUserService.save(tbUser);

        if (user != null) {

            regService.sendEmail(user);
            return success(request.getRequestURI(),user);
        }

        // 注册失败
        return error("注册失败，请重试", null);
    }
}
