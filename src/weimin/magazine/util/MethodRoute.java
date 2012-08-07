package weimin.magazine.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据不同的ajax请求，路由到对应的执行方法
 * 
 * @author lify
 *
 */
public class MethodRoute {
	
	/**
	 * 通过反射机制，执行对应的方法
	 * 
	 * @param request 
	 * @param response
	 * @param methodName 方法名称
	 * @param className 类名称
	 * @throws Exception
	 */
	public static void route(HttpServletRequest request, HttpServletResponse response, String methodName, String className) throws Exception {
		Class<?> cls = null;
		Method method = null;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("ClassNotFoundException");
		}
		
		try {
			method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("SecurityException");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("NoSuchMethodException");
		}
		
		try {
			method.invoke(cls.newInstance(), request, response);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("IllegalArgumentException");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("IllegalAccessException");
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("InvocationTargetException");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("InstantiationException");
		}
	}
}
