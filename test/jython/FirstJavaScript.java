package jython;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class FirstJavaScript {
    public static void main(String args[]) {

        //example 1 在java类中直接执行python语句
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
        interpreter.exec("print days[1];");
        
//        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
//        System.out.println(System.getProperty("user.dir")+"\\test\\jython\\my_utils.py");
        
        //example 2 在java中调用本机python脚本中的函数：
        interpreter.execfile(System.getProperty("user.dir")+"\\test\\jython\\my_utils.py");  
        PyFunction func = (PyFunction)interpreter.get("adder",PyFunction.class); 
        int a = 2010, b = 2 ;  
        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));  
        System.out.println("anwser = " + pyobj.toString());
        
        //example3.使用java直接执行python脚本
        interpreter.execfile(System.getProperty("user.dir")+"\\test\\jython\\input.py"); 
        
        
        

    }// main
} 
