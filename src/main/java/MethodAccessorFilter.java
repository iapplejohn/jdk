//import sun.jvm.hotspot.oops.InstanceKlass;
//import sun.jvm.hotspot.tools.jcore.ClassFilter;
//
///**
// * @author zhujiang.cheng
// * @since 2020/6/29
// */
//public class MethodAccessorFilter implements ClassFilter {
//
//    @Override
//    public boolean canInclude(InstanceKlass instanceKlass) {
//        String klassName = instanceKlass.getName().asString();
//        return klassName.startsWith("sun/reflect/GeneratedMethodAccessor");
//    }
//}
