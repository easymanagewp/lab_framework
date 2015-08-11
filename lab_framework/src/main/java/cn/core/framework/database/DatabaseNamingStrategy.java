package cn.core.framework.database;

import org.hibernate.cfg.DefaultComponentSafeNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
public class DatabaseNamingStrategy extends DefaultComponentSafeNamingStrategy {
	private static final long serialVersionUID = 4614294361681654588L;
	public static final NamingStrategy INSTANCE = new DatabaseNamingStrategy();
	private String tablePrefix = "v_" ;// 数据库表名前缀
 
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	protected static String addUnderscores(String name) {
		if (name == null) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer(name.replace('.', '_'));
		for (int i = 1; i < stringBuffer.length(); i++) {
			if (Character.isUpperCase(stringBuffer.charAt(i))) 
				stringBuffer.insert(i++, '_');
		}
		return stringBuffer.toString().toLowerCase();
	}
	
	public String classToTableName(String className) {
		return tablePrefix+ super.classToTableName(addUnderscores(className));
	}

	public String collectionTableName(String ownerEntity,
			String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName) {
			return tablePrefix + super.collectionTableName(
											addUnderscores(ownerEntity),
											addUnderscores(ownerEntityTable),
											addUnderscores(associatedEntity),
											addUnderscores(associatedEntityTable),
											addUnderscores(propertyName));
	}

	public String logicalCollectionTableName(String tableName,
			String ownerEntityTable, String associatedEntityTable,
			String propertyName) {
			return tablePrefix+ super.logicalCollectionTableName(
											addUnderscores(tableName),
											addUnderscores(ownerEntityTable),
											addUnderscores(associatedEntityTable),
											addUnderscores(propertyName));
	}

	public String propertyToColumnName(String propertyName) {
			return	super.propertyToColumnName(addUnderscores(propertyName));
	}

	public String foreignKeyColumnName(String propertyName,
			String propertyEntityName, String propertyTableName,
			String referencedColumnName) {
			return super.foreignKeyColumnName(
					addUnderscores(propertyName),
					addUnderscores(propertyEntityName),
					addUnderscores(propertyTableName),
					addUnderscores(referencedColumnName));
	}

	public String logicalCollectionColumnName(String columnName,
			String propertyName, String referencedColumn) {
			return super.logicalCollectionColumnName(
					addUnderscores(columnName), addUnderscores(propertyName),
					addUnderscores(referencedColumn));
	}

	public String logicalColumnName(String columnName, String propertyName) {
			return super.logicalColumnName(
					addUnderscores(columnName), addUnderscores(propertyName));
	}

	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
			return super.joinKeyColumnName(
					addUnderscores(joinedColumn), addUnderscores(joinedTable));
	}
	
	 public String tableName(String tableName) {
	  return tablePrefix + super.tableName(tableName);
	 }

}