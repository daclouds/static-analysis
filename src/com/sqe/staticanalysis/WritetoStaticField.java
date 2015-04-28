/*
 * http://172.19.103.24:8000/drilldown/violations/623?&rule=findbugs%3AST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD&rule_sev=MAJOR&severity=MAJOR#
 * http://172.19.103.24:8000/api/sources?resource=com.skp.momenT:com.skp.util.IdmsThreadUtil&format=txt
 */

package com.sqe.staticanalysis;

public class WritetoStaticField extends Thread {

	String usr_id = ""; // LOGIN ID
	String usr_ip = ""; // 사용자 IP
	public static String result = ""; // return 변수

	public WritetoStaticField(HttpServletRequest request) {

		usr_id = request.getParameter("usrId");
		String userIp = request.getRemoteHost();
		userIp = request.getHeader("X-FORWARDED-FOR");
		if (userIp == null) {
			userIp = request.getRemoteHost();
		}
		usr_ip = request.getRemoteHost();

	}

	public void run() {

		AdminUser vo = new AdminUser();
		vo.setSys_id("SERVICE_NAME");
		vo.setUsr_id(usr_id);

		vo.setClnt_ip(usr_ip);

		String host = "idms_if_url";

		result = HttpClient.requestHttp(vo, "", host, "IDMS", "get");

	}

	static class AdminUser {

		public void setSys_id(String string) {
			// TODO Auto-generated method stub

		}

		public void setClnt_ip(String usr_ip) {
			// TODO Auto-generated method stub

		}

		public void setUsr_id(String usr_id) {
			// TODO Auto-generated method stub

		}

	}

	static class HttpServletRequest {

		public String getParameter(String string) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getRemoteHost() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getHeader(String string) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	static class HttpClient {

		public static String requestHttp(AdminUser vo, String string, String host, String string2, String string3) {
			return "Static String";
		}

	}

}
