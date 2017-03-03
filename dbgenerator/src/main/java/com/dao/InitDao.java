package com.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;

public class InitDao {
    private final static String PROJECT_DIR = System.getProperty("user.dir");
    private final static String PACKAGE_NAME = "com.internship.pbt.findimage.db";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, PACKAGE_NAME);
       // createEntities(schema);
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    /*private static void createEntities(Schema schema) {
        addItem(schema);
    }

    private static Entity addItem(Schema schema) {
        Entity item = schema.addEntity("Images");
        item.addIdProperty().primaryKey().autoincrement();
        item.addStringProperty("kind");
        item.addStringProperty("title");
        item.addStringProperty("htmlTitle");
        item.addStringProperty("link");
        item.addStringProperty("displayLink");
        item.addStringProperty("snippet");
        item.addStringProperty("htmlSnippet");
        item.addStringProperty("mime");
        item.addStringProperty("image");
        item.addStringProperty("fileFormat");
        return item;
    }*/
}
