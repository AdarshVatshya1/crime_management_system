package crime.management.system;

import javax.swing.table.AbstractTableModel;
import java.sql.*;

public class ResultSetTableModel extends AbstractTableModel {

    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int columnCount;


    public ResultSetTableModel(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        this.metaData = resultSet.getMetaData();
        this.columnCount = metaData.getColumnCount();
    }


    @Override
    public int getRowCount() throws IllegalStateException {
        try {
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            return rowCount;
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to get row count", e);
        }
    }


    @Override
    public int getColumnCount() {
        return columnCount;
    }


    @Override
    public String getColumnName(int column) {
        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }


    @Override
    public Object getValueAt(int row, int column) {
        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
