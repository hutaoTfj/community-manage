package ink.hutao.manage.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.xiaoTools.core.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
@RestControllerAdvice
public class GlobalException {

    /**
     * <p>全局异常拦截（拦截项目中的所有异常）</p>
     * @author tfj
     * @since 2021/7/13
     */
    @ResponseBody
    @ExceptionHandler
    public Result handlerException(Exception e, HttpServletRequest request){

        // 打印堆栈，以供调试
        System.out.println("全局异常---------------");
        e.printStackTrace();

        // 不同异常返回不同状态码
        Result result;
        if (e instanceof NotLoginException) {
            result = new Result().result403("未登录异常,禁止操作",request.getRequestURI());
        } else if (e instanceof NullPointerException){
            result=new Result().result500("空指针异常",request.getRequestURI());
        } else if(e instanceof NotRoleException) {
            result = new Result().result403("无此权限，禁止操作",request.getRequestURI());
        }else {
            result=new Result().result500("普通异常",request.getRequestURI());
        }
        return result;
    }
}
