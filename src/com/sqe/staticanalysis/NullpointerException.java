package com.sqe.staticanalysis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NullpointerException {

	private static final String NO_COLLECT_MESSAGE = "%s 서비스 %s 단말 데이터가 %s 분간 수집되지 않고 있습니다.";
	private static final String NO_COLLECT_MESSAGE_WITHOUT_SERVICE_AND_DEVICE_NAME = "데이터가 %s 분간 수집되지 않고 있습니다.";

	private static final String DUMMY_LOG_SERVICE_NAME = "sqe-test";
	private static final String LAST_INSERTED_LOG_KEY = "lastInsertedLog";

	private Map<String, TestLoadingLog> lastInsertedLogKeep = new HashMap<String, TestLoadingLog>();

	private boolean useNotification;
	private String messageTypeNoCollect;
	private String sqeGroup;
	private long termOfNoCollectMilliSeconds;

	public void notifyNoCollect(TestLoadingLog lastInsertedLog, long accumulatedMinutes) {
		if (useNotification) {
			try {
				Map<String, String> uriVariables = new HashMap<String, String>();
				uriVariables.put("messageTypeName", messageTypeNoCollect);
				uriVariables.put("groupName", sqeGroup);

				Map<String, Object> requestMap = new HashMap<String, Object>();
				Map<String, Object> groupInfo = new HashMap<String, Object>();
				// requestMap.put("errorMessage", NO_COLLECT_MESSAGE);o

				if (lastInsertedLog == null) {
					requestMap.put("errorMessage",
							String.format(NO_COLLECT_MESSAGE_WITHOUT_SERVICE_AND_DEVICE_NAME, accumulatedMinutes));
				} else {
					requestMap.put(
							"errorMessage",
							String.format(NO_COLLECT_MESSAGE, lastInsertedLog.getServiceName(),
									lastInsertedLog.getDeviceName(), accumulatedMinutes));
				}
				groupInfo.put("system", "StopWatch");
				groupInfo.put("name", sqeGroup);
				requestMap.put("group", groupInfo);

			} catch (Exception e) {
			}
		}
	}

	public void checkIsNoCollectStatus() {
		if (useNotification) {
			TestLoadingLog lastInsertedLog = lastInsertedLogKeep.get(LAST_INSERTED_LOG_KEY);
			long accumulatedTime = 0;
			if (lastInsertedLog != null && lastInsertedLog.getActionDate() != null) {
				accumulatedTime = System.currentTimeMillis() - lastInsertedLog.getActionDate().getTime();
			}
			if (lastInsertedLog == null || accumulatedTime > termOfNoCollectMilliSeconds) {
				if (!DUMMY_LOG_SERVICE_NAME.equals(lastInsertedLog.getServiceName())) {
					notifyNoCollect(lastInsertedLog, TimeUnit.MILLISECONDS.toMinutes(accumulatedTime));
				}
			}
		}
	}

	class TestLoadingLog {

		public Date getActionDate() {
			return new Date();
		}

		public String getDeviceName() {
			return "DEVICE_NAME";
		}

		public String getServiceName() {
			return "SERVICE_NAME";
		}

	}
}
