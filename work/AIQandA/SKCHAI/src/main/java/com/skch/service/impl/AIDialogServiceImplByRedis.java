package com.skch.service.impl;

import com.skch.service.DialogsService;

public class AIDialogServiceImplByRedis implements DialogsService{

	@Override
	public int addSentence(String sender, String accepter, String scripts, String askId, String dialog) {
		System.out.println("AIDialogServiceImplByRedis");
		return 0;
	}

}
