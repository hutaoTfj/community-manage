package ink.hutao.manage.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类的工具类
 *
 * @author HCY
 * @since 2021/1/1 下午4:14
*/
public class BeansUtils {

    /**
     * 对象之间的拷贝
     *
     * @author HCY
     * @since 2021/1/1 下午4:16
     * @param source: 来源(类似于DTO，PO)
     * @param classType: 接受源(类似于PO，VO)
     * @return E
    */
    public static <T,E> E beanCopy(T source,Class<E> classType){
        //判断来源是否为控
        if (source == null){
            return null;
        }
        E target = null;
        try {
            target = classType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source,target);
        return target;
    }

    /**
     * 集合对象之间的拷贝
     *
     * @author HCY
     * @since 2021/1/3 上午9:21
     * @param sourceList: 来源的list集合
     * @param classType: 接受源的class
     * @return java.util.List<E>
    */
    public static <T,E> List<E> listCopy(List<T> sourceList,Class<E> classType){
        if (sourceList == null){
            return null;
        }
        List<E> target = new ArrayList<E>();
        for (T source : sourceList){
            target.add(beanCopy(source,classType));
        }
        return target;
    }
}
