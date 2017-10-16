package com.skch.service.impl;

import org.dom4j.Element;

import com.skch.bean.AskLabelPosition;
import com.skch.service.StackService;
import com.skch.util.ReadXmlUtil;

import redis.clients.jedis.Jedis;

public class AIStackServiceImpl implements StackService {

	private Jedis jedis;
	
	public AIStackServiceImpl() {
		jedis = new Jedis("localhost");
	}

	@Override
	public void push(String userId, AskLabelPosition askPos) {
		String scriptsFile = askPos.getScriptsFile();
		String askId = askPos.getAskId();
		String location = scriptsFile + "," + askId;
		jedis.lpush(userId, location);
		jedis.close();
	}

	@Override
	public AskLabelPosition pop(String userId) {
		AskLabelPosition askLabelPosition = null;
		String location = jedis.lpop(userId);
		String fileName = "";
		String askId = "";
		if(location != null && !location.equals("")){
			String[] split = location.split(",");
			fileName = split[0];
			askId = split[1];
			ReadXmlUtil readXmlUtil = new ReadXmlUtil();
			Element element = readXmlUtil.getElement(fileName, askId);
			Element root = readXmlUtil.getRoot(fileName);
			Element next = readXmlUtil.getNext(element, root);
			if(next != null){
				askLabelPosition = new AskLabelPosition(fileName,next.getName());
			}
		}else {
			fileName = "";
			askId = "";
			askLabelPosition = new AskLabelPosition(fileName,askId);
		}
		jedis.close();
		return askLabelPosition;
	}

	@Override
	public void flash(String... keys) {
		for (String key : keys) {
			jedis.del(key);
		}
	}

}
