package org.comstudy21.controller;

import org.comstudy21.model.Member;

public class InputController implements Controller {

	public InputController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service() {
		Member dto = new Member();
		String name = nameField.getText();
		String email = emailField.getText();
		String phone = phoneField.getText();
		dto.setName(name);
		dto.setEmail(email);
		dto.setPhone(phone);
		dao.insert(dto);
	}

}
