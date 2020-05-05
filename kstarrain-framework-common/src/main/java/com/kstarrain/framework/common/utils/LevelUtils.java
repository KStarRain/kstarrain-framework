package com.kstarrain.framework.common.utils;



import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: DongYu
 * @create: 2019-01-31 13:06
 * @description:
 */
public class LevelUtils {
	
    private static final String ROOT = "ROOT";

    /**
     * 将平级数据多级化，层次化
     * @param src 平级数据集合
     * @param sonListName 下级数据集合 属性名
     * @param idName  当前数据的唯一关联标识 属性名
     * @param pidName 上级数据的唯一关联标识 属性名
     * @param relationNames 需要往唯一标识后拼接的 属性名(比如 多种菜单的组合方式的参数)
     * @return 层级化后的数据集合
     * @throws ReflectiveOperationException 反射异常
     */
    public static <T> List<T> formatLevel(List<T> src, String sonListName, String idName, String pidName, String... relationNames) throws ReflectiveOperationException{
    	
    	//如果平级数据集合为空，返回null
        if (CollectionUtils.isEmpty(src)) { return null; }
        
        //根据平级数据集合的第一个值来获取类型
        Class<?> clazz = src.get(0).getClass();
        
        //通过反射获得当前数据的唯一关联标识的get方法
        Method getIdMethod = clazz.getMethod("get" + StringUtils.capitalize(idName));
        
        //通过反射获得上级数据的唯一关联标识的get方法
        Method getPidMethod = clazz.getMethod("get" + StringUtils.capitalize(pidName));
        
        //通过反射获得下级数据集合的set方法
        Method setSonListMethod = clazz.getMethod("set" + StringUtils.capitalize(sonListName), List.class);
        
        //构建集合(存储通过反射获得需要往唯一标识后拼接的属性的get方法)
        List<Method> getRelationMethods = new ArrayList<>();
        for (String relationName : relationNames) {
            Method getRelationMethod = clazz.getMethod("get" + StringUtils.capitalize(relationName));
            getRelationMethods.add(getRelationMethod);
        }
        
        //判断是否需要往唯一标识后拼接属性
        boolean hasRelation = getRelationMethods.size() > 0;

        //构建临时map存储数据(通过引用传递来进行层级关系的构建)
        Map<String, List<T>> map = new HashMap<>(src.size() + 1);
        map.put(ROOT, new ArrayList<T>());
       
        //遍历平级数据集合获得每条数据
        for (T obj : src) {
        	System.out.println(obj);
        	//执行get方法获得当前数据的唯一关联标识
        	Object id = getIdMethod.invoke(obj);
        	
        	//执行get方法获得上级数据的唯一关联标识
            Object pid = getPidMethod.invoke(obj);
            
            //构建字符串缓冲区(存储执行get方法获得的需要往唯一标识后拼接的属性)
            StringBuilder relation = new StringBuilder();
            if (hasRelation) {
                for (Method getRelationMethod : getRelationMethods) {
                    relation.append(getRelationMethod.invoke(obj));
                }
            }
            
            //组建当前唯一标识key
            String idKey = id == null ? "" : id + "";
            idKey = hasRelation ? idKey + relation : idKey;
            
            //根据当前标识key去临时map中获取下级数据集合
            List<T>  sonList = map.get(idKey);
            if (sonList == null) {
            	sonList = new ArrayList<>();
                map.put(idKey, sonList);
            }
            //执行set方法向当前对象的下级数据集合中设置数据(其实是将集合的地址存入，当在外界从map中获得这个集合并修改数据时，所有包含这个集合地址的值全会改变)
            setSonListMethod.invoke(obj, sonList);
            
            String pidKey = pid == null ? "" : pid + "";
            
            //若遍历的当前对象无上级唯一标识,说明为根元素(一级数据)
            if (StringUtils.isBlank(pidKey)) {
            	
            	//将当前元素(索引地址)添加入ROOT对应的value集合中
            	map.get(ROOT).add(obj); 
            }else {
            	
            	//组建上级唯一标识key
            	pidKey = hasRelation ? pidKey + relation : pidKey;

				//将sonList重新赋值(根据上级标识key去临时map中获取下级数据集合)
				sonList = map.get(pidKey);
				if (sonList == null) {
				 	sonList = new ArrayList<>();
				    map.put(pidKey, sonList);
				}
                sonList.add(obj);
			}
           
        }

        return map.get(ROOT);
    }

}
