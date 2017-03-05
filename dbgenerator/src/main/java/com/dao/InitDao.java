package com.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;

public class InitDao {
    private final static String PROJECT_DIR = System.getProperty("user.dir");
    private final static String PACKAGE_NAME = "com.internship.pbt.findimage.db";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, PACKAGE_NAME);
        new DaoGenerator().generateAll(schema, PROJECT_DIR);
    }
}
