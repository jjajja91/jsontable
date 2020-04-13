package org.comstudy21.controller;

import java.util.Vector;

import org.comstudy21.model.Member;

public class SearchController implements Controller {

	@Override
	public void service() {
		Member dto = new Member();
		String name = nameField.getText();
		String email = emailField.getText();
		String phone = phoneField.getText();
		dto.setName(name);
		dto.setEmail(email);
		dto.setPhone(phone);
		
		Vector<Vector> dataList = dao.selectSome(dto);

		if (dataList != null) {
			while (dm.getRowCount() > 0) {
				dm.removeRow(0);
			}
			for (Vector rowData : dataList) {
				dm.addRow(rowData);
			}
		} else {
			System.out.println("일치하는 데이터가 없습니다!");
		}

	}

}
