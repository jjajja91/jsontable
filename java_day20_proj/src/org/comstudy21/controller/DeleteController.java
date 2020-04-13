package org.comstudy21.controller;

import java.util.Vector;

public class DeleteController implements Controller {

	@Override
	public void service() {

		int[] rowArray = listView.table.getSelectedRows();
		Vector<Integer> idxVector = new Vector<>();
		for (int i = 0; i < rowArray.length; i++) {
			int index = Integer.parseInt("" + dm.getValueAt(rowArray[i], 0));
			idxVector.add(index);
		}
		dao.delete(idxVector);

	}

}
