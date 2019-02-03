package io.annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName io.annotations.database.TableCreator
 * @Description 20.2.5 实现处理器<p>
 * 下面是一个注解处理器的例子, 它将读取一个类文件,检查其上的数据库注解, 并生成用来创建数据库的SQL命令
 * 
 * @see io.annotations.database.DBTable
 * @see io.annotations.database.Constraints
 * @see io.annotations.database.SQLString
 * @see io.annotations.database.SQLInteger
 * 
 * @author daydaylw3
 * @date 2019年2月3日
 */
public class TableCreator {
	// 传参为 io.annotations.database.Member
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.out.println("arguements: annotated classes");
			System.exit(0);
		}
		for (String className : args) {
			Class<?> cl = Class.forName(className);
			// 检查是否带有@DBTable注解
			DBTable dbTable = cl.getAnnotation(DBTable.class);
			if (dbTable == null) {
				System.out.println("no dbTable annotations in class " + className);
				continue;
			}
			String tableName = dbTable.name();
			// if the name is empty, use the class name
			if (tableName.length() < 1) {
				tableName = cl.getName().toUpperCase();
			}
			List<String> columnDefs = new ArrayList<String>();
			for (Field field : cl.getDeclaredFields()) {
				String columnName = null;
				Annotation[] anns = field.getDeclaredAnnotations();
				if (anns.length < 1) {
					continue;
				}
				if (anns[0] instanceof SQLInteger) {
					SQLInteger sInt = (SQLInteger)anns[0];
					if (sInt.name().length() < 1) {
						columnName = field.getName().toUpperCase();
					} else {
						columnName = sInt.name();
					}
					columnDefs.add((columnName) + " INT" + getConstraints(sInt.constraints()));
				}
				if (anns[0] instanceof SQLString) {
					SQLString sString = (SQLString)anns[0];
					if (sString.name().length() < 1) {
						columnName = field.getName().toUpperCase();
					} else {
						columnName = sString.name();
					}
					columnDefs.add((columnName) + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()));
				}
				StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
				for (String columnDef : columnDefs) {
					createCommand.append("\n   " + columnDef + ",");
				}
				// remove trailing comma
				String tableCreate = createCommand.substring(0, createCommand.length() - 1) + ");";
				System.out.println("Table Creation SQL for " + className + " is :\n" + tableCreate);
			}
		}
	}
	
	private static String getConstraints(Constraints con) {
		String constraints = "";
		if (!con.allowNull()) {
			constraints += " NOT NULL";
		}
		if (con.primaryKey()) {
			constraints += " PRIMARY KEY";
		}
		if (con.unique()) {
			constraints += " UNIQUE";
		}
		return constraints;
	}
}
