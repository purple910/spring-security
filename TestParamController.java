package com.example.demo.controller;

import com.example.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestParamController
 * @Description
 * @PackageName com.example.demo.controller.TestParamController
 * @Author admin
 * @Date 2020/11/20    11:49
 **/
@Controller
@RequestMapping("/param")
public class TestParamController {
    private static final Logger logger = LoggerFactory.getLogger(TestParamController.class);
    /**
     * 请求参数名和Controller方法的参数一致
     * produces 设置返回参数的编码格式 可以设置返回数据的类型以及编码，可以是json或者xml
     * {
     *     @RequestMapping(value="/xxx",produces = {"application/json;charset=UTF-8"})
     *      或
     *     @RequestMapping(value="/xxx",produces = {"application/xml;charset=UTF-8"})
     *      或
     *     @RequestMapping(value="/xxx",produces = "{text/html;charset=utf-8}")
     * }
     * @param name 用户名
     * @param pwd 密码
     * @return
     *
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addUser(String name, String pwd){
        logger.debug("name:" + name + ",pwd:" + pwd);
        return "name:" + name + ",pwd:" + pwd;
    }

    /**
     * 自定义方法参数名-当请求参数名与方法参数名不一致时
     * @param u_name 用户名
     * @param u_pwd 密码
     * @return
     */
    @RequestMapping(value = "/addByDifName", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addUserByDifName(@RequestParam(name="name",defaultValue = "李四") String u_name, @RequestParam("pwd")String u_pwd){
        logger.debug("name:" + u_name + ",pwd:" + u_pwd);
        return "name:" + u_name + ",pwd:" + u_pwd;
    }

    /**
     * 请求参数名和Controller方法的参数一致
     * produces 设置返回参数的编码格式 可以设置返回数据的类型以及编码，可以是json或者xml
     * @param user 用户信息
     * @return
     *
     */
    @RequestMapping(value = "/addByObject", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addUserByObject(User user){
        logger.debug("name:" + user.getName() + ",pwd:" + user.getPwd());
        return "name:" + user.getName() + ",pwd:" + user.getPwd();
    }

    /**
     * RequestBody-JSON 对象方式
     * @param user
     * @return
     */
    @RequestMapping(value = "/addByObjectJSON", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addUserByObjectJSON(@RequestBody User user){
        logger.debug("name:" + user.getName() + ",pwd:" + user.getPwd());
        return "name:" + user.getName() + ",pwd:" + user.getPwd();
    }

    /**
     * RequestBody-JSON List对象方式
     * @param users
     * @return
     */
    @RequestMapping(value = "/addByListJSON", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addUsersByListJSON(@RequestBody List<User> users){
        StringBuilder sb = new StringBuilder("{");
        if(null != users){
            for(User user : users){
                sb.append("{" + "name:" + user.getName() + ",pwd:" + user.getPwd() + "}");
            }
        }
        sb.append("}");
        logger.debug(sb.toString());
        return sb.toString();
    }
    /**
     * RequestBody-JSON Map对象方式
     * @param users
     * @return
     */
    @RequestMapping(value = "/addByMapJSON", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addUsersByMapJSON(@RequestBody Map<String, User> users){
        StringBuilder sb = new StringBuilder("{");
        if(null != users){
            Iterator it = users.keySet().iterator();
            while(it.hasNext()){
                User user = users.get(it.next());
                sb.append("{" + "name:" + user.getName() + ",pwd:" + user.getPwd() + "}");
            }
        }
        sb.append("}");
        logger.debug(sb.toString());
        return sb.toString();
    }
    /**
     * 通过HttpServletRequest接收
     * @param request
     * @return
     */
    @RequestMapping(value = "/addByHttpServletRequest", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addUserByHttpServletRequest(HttpServletRequest request){
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        logger.debug("name:" + name + ",pwd:" + pwd);
        return "name:" + name + ",pwd:" + pwd;
    }

    /**
     * 通过@PathVariable获取路径中的参数
     * @param name 用户名
     * @param pwd 密码
     * @return
     */
    @RequestMapping(value = "/add/{name}/{pwd}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addUserByPathVariable(@PathVariable String name, @PathVariable String pwd){
        logger.debug("name:" + name + ",pwd:" + pwd);
        return "name:" + name + ",pwd:" + pwd;
    }
}
