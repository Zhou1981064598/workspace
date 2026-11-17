package com.example.springbootstudy.AOP;
import com.example.springbootstudy.db.Jdbc;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CutPoint {
    @Autowired
    Jdbc jdbc;
    //增强哪些函数，函数名就是切入点的名称
    @Pointcut("execution(* com.example.springbootstudy.db.JdbcUtil.find(..))")
    public void cutTargetAll(){
    }
//    @Around("cutTargetAll()")
//    public Page around(ProceedingJoinPoint pjp){
//        System.out.println("1");
//        try {
//            return pjp.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//            System.out.println("2");
//        }
//        return "around";
//        try {
//            Connection connection=jdbc.getConnection();
//            try {
//                Statement statement=connection.createStatement();
//                ResultSet total=statement.executeQuery("select count (*) from user");
//                ResultSet resultSet=statement.executeQuery("select * from user limit 0,5");
//                List<User> userList=new LinkedList<>();
//
//            }
//            catch (SQLException e){
//                e.printStackTrace();
//                return null;
//            }
//        }catch (Throwable e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }

//    //在被代理函数执行【前】执行该代码
//    @Before("cutTargetAll()")
//    public void before(){
//        System.out.println("before");
//    }
//    //在被代理函数执行【后】执行该代码
//    @After("cutTargetAll()")
//    public void after(){
//        System.out.println("after");
//    }
//    @AfterReturning("cutTargetAll()")
//    //在被代理函数执行【返回值后】执行该代码
//    public void afterreturning(){
//        System.out.println("afterreturning");
//    }
//    //在被代理函数执行【抛异常后】执行该代码
//    @AfterThrowing("cutTargetAll()")
//    public void afterthrowing(){
//        System.out.println("afterthrowing");
//    }
//    //自定义、个性化pjp相当于是目标对象的原本的函数=test1
//    @Around("cutTargetAll()")
//    public Object around(ProceedingJoinPoint pjp){
//        System.out.println("1");
//        try {
//            return pjp.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//            System.out.println("2");
//        }
//        return "around";
//    }

}
