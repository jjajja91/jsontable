package org.comstudy21.controller;

import java.util.Vector;

public class ListController implements Controller {

	public ListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service() {
		Vector<Vector> dataList = dao.selectAll();

		if (dataList != null) {
			while (dm.getRowCount() > 0) {
				dm.removeRow(0);
			}
			for (Vector rowData : dataList) {
				dm.addRow(rowData);
			}
		} else {
			System.out.println("출력 할 데이터가 없습니다!");
		}
	}

	public void test() {
		Vector rowData = new Vector<>();
		rowData.add(0);
		rowData.add("test");
		rowData.add("test@a.com");
		rowData.add("010-1111-1111");
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}
		dm.addRow(rowData);

	}

}
