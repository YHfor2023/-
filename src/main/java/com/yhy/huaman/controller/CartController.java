package com.yhy.huaman.controller;

import com.yhy.huaman.service.ICartService;
import com.yhy.huaman.util.JsonResult;
import com.yhy.huaman.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, String color, String size,HttpSession session) {
        System.err.println(color+size);
        cartService.addToCart(
                getUidFromSession(session),
                pid,
                amount,
                color,
                size,
                getUsernameFromSession(session));
        return new JsonResult<Void>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        List<CartVO> data = cartService.getVOByUid(getUidFromSession(session));
        return new JsonResult<List<CartVO>>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.addNum(
                cid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<Integer>(OK, data);
    }

    @RequestMapping("{cid}/num/sub")
    public JsonResult<Integer> subNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.subNum(
                cid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<Integer>(OK, data);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> findVOByCids(Integer[] cids, HttpSession session) {
        List<CartVO> data = cartService.getVOByCids(getUidFromSession(session), cids);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{cid}/delete")
    public JsonResult<Integer> delete(@PathVariable("cid") Integer cid, HttpSession session) {
        cartService.deletebyCid(cid,getUidFromSession(session));
        return new JsonResult<Integer>(OK);
    }

}

