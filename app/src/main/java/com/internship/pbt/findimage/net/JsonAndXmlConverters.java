package com.internship.pbt.findimage.net;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class JsonAndXmlConverters {
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Json{

    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Xml{

    }
}
