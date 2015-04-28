package com.sqe.staticanalysis;

import java.util.Date;

public class NullpointerDereference {
	AttendanceDAO attendanceDAO = new AttendanceDAO();

	private void setAttendTimeSet() throws Exception {
		AttendTime attendTime = attendanceDAO.selectAttendTimeSet(new Date().getTime());

		if (attendTime != null) {
			try {
				if (Integer.parseInt("20150101") <= Integer.parseInt(attendTime.toString())) {
					throw new Exception("Exception");
				}
			} catch (NumberFormatException e) {
				/* ignored */
			}
		}

		attendTime.setEndTime(new Date().getTime());
	}

	class AttendanceDAO {

		public AttendTime selectAttendTimeSet(long time) {
			Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);

			if (time < yesterday.getTime()) {
				return null;
			}
			return new AttendTime(time);
		}

	}

	class AttendTime {
		Date endTime = null;
		Date startTime = null;

		AttendTime(long time) {
			startTime = new Date(time);
		}

		public void setEndTime(long time) {
			endTime = new Date(time);
		}

		public Date getStartTime() {
			return startTime;
		}

		public Date getEndTime() {
			return endTime;
		}
	}
}
