package com.liubo.base.basis._18throwable;

/**
 * @ClassName: ExceptionChainExample
 * @ProjectName scalademo
 * @Author liubo
 * @Date 2018-09-30 13:22
 * @Description:
 *异常链顾名思义就是将异常发生的原因一个传一个串起来，即把底层的异常信息
 * 传给上层，这样逐层抛出。 Java API文档中给出了一个简单的模型：
 *
 * 当程序捕获到了一个底层异常，在处理部分选择了继续抛出一个更高级别的新
 * 异常给此方法的调用者。 这样异常的原因就会逐层传递。这样，位于高层的
 * 异常递归调用getCause()方法，就可以遍历各层的异常原因。 这就是Java异
 * 常链的原理。异常链的实际应用很少，发生异常时候逐层上抛不是个好注意，
 * 上层拿到这些异常又能奈之何？而且异常逐层上抛会消耗大量资源， 因为要
 * 保存一个完整的异常链信息.
 */
public class ExceptionChainExample {
//    static void exceptionChain(){
//        try{
//            lowLevelOp();
//        }catch(LowLevelException le){
//            throw (HighLevelException) new HighLevelException().initCause(le);
//        }
//    }
}
