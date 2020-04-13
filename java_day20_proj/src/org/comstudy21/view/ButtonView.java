package org.comstudy21.view;

import javax.swing.JButton;

import org.comstudy21.evt_handler.BtnEvtHandler;

public class ButtonView extends View {

	public JButton allBtn = new JButton("전체보기");
	public JButton inputBtn = new JButton("추가");
	public JButton deleteBtn = new JButton("삭제");
	public JButton searchBtn = new JButton("검색");
	public JButton cancelBtn = new JButton("취소");

	
	
	public ButtonView() {
		init();
	}

	@Override
	public void init() {
		add(allBtn);
		add(inputBtn);
		add(searchBtn);
		add(deleteBtn);
		add(cancelBtn);
		start();
	}

	@Override
	public void start() {
		BtnEvtHandler handler = new BtnEvtHandler(this);		
		allBtn.addActionListener(handler);
		inputBtn.addActionListener(handler);
		searchBtn.addActionListener(handler);
		deleteBtn.addActionListener(handler);
		cancelBtn.addActionListener(handler);
	}

}
