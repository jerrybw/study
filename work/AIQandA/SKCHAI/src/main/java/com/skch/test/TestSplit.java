package com.skch.test;

public class TestSplit {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String str = "aaa aaa";
		if(str.contains(" ")){
			System.out.println(str.indexOf(" "));
//			String substring = str.substring(prefix, suffix + 2);
//			String substring2 = str.substring(prefix + 2, suffix);
//			String[] split = substring2.split("\\.");
//			System.out.println(split.length);
//			UserQuestion userQuestion = new UserQuestion();
//			Answer answer = new Answer();
//			answer.setAskId("001");
//			answer.setScript("daoyi.xml");
//			answer.setValue("aaa");
//			userQuestion.setAnswer(answer);
//			userQuestion.setClientId("010");
//			userQuestion.setDeviceId("002");
//			userQuestion.setLatitude("003");
//			userQuestion.setLongitude("004");
//			userQuestion.setQuestion("005");
//			userQuestion.setQuestionId("006");
//			userQuestion.setUserId("007");
//			userQuestion.setUserType("008");
//			System.out.println(str);
//			Class clazz =  userQuestion.getClass();
//			String filedName = split[0];
//			Field field2 = clazz.getDeclaredField(filedName);
//			field2.setAccessible(true);
//			Class<?> type = field2.getType();
//			boolean instance = type.isInstance(Object.class);
//			System.out.println(instance);
//			System.out.println(type.toString());
//			Object object = field2.get(userQuestion);
//			System.out.println(object);
//			Field[] fields = clazz.getDeclaredFields();
////			for (Field field : fields) {
////				System.out.println(field.getName());
////			}
//			System.out.println(substring);
//			System.out.println(type.equals(String.class));
////			System.out.println(replace);
		}else {
			System.out.println("aaa");
		}
	}
	
}
