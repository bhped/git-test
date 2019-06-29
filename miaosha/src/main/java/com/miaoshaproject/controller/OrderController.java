package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/createOrder")
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "userId") Integer userId,
                                        @RequestParam(name = "itemId") Integer itemId,
                                        @RequestParam(name = "promoId") Integer promoId,
                                        @RequestParam(name = "amount")Integer amount) throws BusinessException {
//        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
//        if (isLogin == null || !isLogin.booleanValue()) {
//            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN, "用户还未登录，不能下单");
//        }
//        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        OrderModel orderModel = orderService.createOrder(userId, itemId, promoId, amount);
        return CommonReturnType.create(orderModel);
    }
}
