package com.example.implementations;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.implementations.models.ColumnMetadata;
import com.example.implementations.models.ForeignKeyMetadata;
import com.example.implementations.models.PrimaryKeyMetadata;
import com.example.implementations.models.TableMetadata;
import com.example.repo.MongoRepo;
import com.zaxxer.hikari.HikariDataSource;

@Service
public class MysqlDatabaseImpl {

	@Autowired
	private HikariDataSource dataSource;

	@Autowired
	private MongoRepo mongoRepo;

	public boolean isConnected() {
		return !dataSource.isClosed();
	}

	public List<TableMetadata> queryForMetadata() {
		Connection connection = null;
		ResultSet resultSet = null;
		List<TableMetadata> list = null;
		try {
			connection = this.dataSource.getConnection();
			DatabaseMetaData data = connection.getMetaData();
			resultSet = data.getTables(null, null, null, new String[] { "TABLE" });
			while (resultSet.next()) {
				if (list == null) {
					list = new ArrayList<TableMetadata>();
				}
				TableMetadata metadata = new TableMetadata();
				List<ColumnMetadata> columnMetadataList = null;
				PrimaryKeyMetadata keyMetadata = null;
				List<ForeignKeyMetadata> foreignKeyMetadata = null;
				// Print
				String tableName = resultSet.getString("TABLE_NAME");
				metadata.setTableName(tableName);
				System.out.println(tableName);
				ResultSet columns = data.getColumns(null, null, tableName, null);
				while (columns.next()) {
					if (columnMetadataList == null)
						columnMetadataList = new ArrayList<ColumnMetadata>();
					String columnName = columns.getString("COLUMN_NAME");
					String datatype = columns.getString("DATA_TYPE");
					String columnsize = columns.getString("COLUMN_SIZE");
					String decimaldigits = columns.getString("DECIMAL_DIGITS");
					String isNullable = columns.getString("IS_NULLABLE");
					String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
					ColumnMetadata columnMetadata = new ColumnMetadata();
					columnMetadata.setColumnName(columnName);
					columnMetadata.setDataType(datatype);
					columnMetadata.setIsAutoIncremented(is_autoIncrment);
					columnMetadata.setSize(Integer.valueOf(columnsize));
					columnMetadata.setNullable(isNullable);
					System.out.println(columnName + "---" + datatype + "---" + columnsize + "---" + decimaldigits
							+ "---" + isNullable + "---" + is_autoIncrment);
					if (columnMetadata != null) {
						columnMetadataList.add(columnMetadata);
					}
				}
				if (columnMetadataList != null) {
					metadata.setColumnData(columnMetadataList);
				}

				ResultSet PK = data.getPrimaryKeys(null, null, tableName);
				while (PK.next()) {
					keyMetadata = new PrimaryKeyMetadata();
					String columnName = PK.getString("COLUMN_NAME");
					String keyName = PK.getString("PK_NAME");
					keyMetadata.setColumnName(columnName);
					keyMetadata.setKeyName(keyName);
					System.out.println(PK.getString("COLUMN_NAME") + "===" + PK.getString("PK_NAME"));
				}
				if (keyMetadata != null) {
					metadata.setPkData(keyMetadata);
				}

				ResultSet FK = data.getImportedKeys(null, null, tableName);
				while (FK.next()) {
					if (foreignKeyMetadata == null) {
						foreignKeyMetadata = new ArrayList<ForeignKeyMetadata>();
					}
					ForeignKeyMetadata foreignKeyMetadata2 = new ForeignKeyMetadata();
					String fkColumnName = FK.getString("FKCOLUMN_NAME");
					String fkTableName = FK.getString("FKTABLE_NAME");
					String pkColumnName = FK.getString("PKCOLUMN_NAME");
					String pkTableName = FK.getString("PKTABLE_NAME");
					foreignKeyMetadata2.setFkColumnName(fkColumnName);
					foreignKeyMetadata2.setFkTableName(fkTableName);
					foreignKeyMetadata2.setPkColumnName(pkColumnName);
					foreignKeyMetadata2.setPkTableName(pkTableName);
					foreignKeyMetadata.add(foreignKeyMetadata2);
					System.out.println(FK.getString("PKTABLE_NAME") + "---" + FK.getString("PKCOLUMN_NAME") + "==="
							+ FK.getString("FKTABLE_NAME") + "---" + FK.getString("FKCOLUMN_NAME"));
				}
				if (foreignKeyMetadata != null) {
					metadata.setFkData(foreignKeyMetadata);
				}
				list.add(metadata);
			}
			mongoRepo.insertData(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
			}
			try {
				resultSet.close();
			} catch (Exception e2) {
			}
		}
		return list;
	}

	public void disConnect() {
		if (dataSource != null) {
			this.dataSource.close();
		}

	}
}
