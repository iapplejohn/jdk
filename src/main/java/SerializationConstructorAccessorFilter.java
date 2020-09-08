import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.tools.jcore.ClassFilter;

/**
 * @author zhujiang.cheng
 * @since 2020/7/1
 */
public class SerializationConstructorAccessorFilter implements ClassFilter {

    @Override
    public boolean canInclude(InstanceKlass instanceKlass) {
        return false;
    }
}
