package com.sqe.staticanalysis;

/**
 * http://172.19.103.24:8000/drilldown/violations/623?&rule=findbugs%3ANP_GUARANTEED_DEREF_ON_EXCEPTION_PATH&rule_sev=BLOCKER&severity=BLOCKER
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class ValueIsNull {
	
	public void logInWrite(String fileName, String userId, String userIp){
		
		
		// file 객체 생성
		File inputFile = new File(fileName);
		
		if(!inputFile.isFile()){
			System.out.println("파일 없으므로 생성");
			try {
				FileWriter fw = new FileWriter(inputFile);
				fw.write("");
				fw.flush();fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		File outputFile = new File(fileName + ".temp");

		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;

		boolean result = false;

		try {

			// 생성
			fileInputStream = new FileInputStream(inputFile);
			fileOutputStream = new FileOutputStream(outputFile);

			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

			// 원본 파일에서 읽어 들이는 한라인
			String line;
			// 패턴에 일치하는 문자로 대체하고 난 후의 string
			String repLine = "";

			// 원본 파일에서 한라인씩 읽는다.
			while ((line = bufferedReader.readLine()) != null) {
				
				if(line.toString().equals("")){
					continue;
				}

				String[] tmpArr = line.split(",", 4);
				String tmpId = tmpArr[0];
				String tmpIp = tmpArr[1];
				String tmpLogInDt = tmpArr[2];
				String tmpLogOutDt = tmpArr[3];

				// 유저의 기록이 있는 Line 캐치
				if (userId.equals(tmpId)) {
					
					
					// 로그인, 로그아웃 기록이 둘다 있으면 정상 로그이므로, pass
					if(!tmpLogInDt.equals("") && !tmpLogOutDt.equals("") ){
						repLine = line;
					}
					
					// 로그인, 로그아웃 둘다 없는 로그는 있을수 없으므로, Line 삭제
					if(tmpLogInDt.equals("") && tmpLogOutDt.equals("")){
						repLine = "";
					}
					
					// 로그아웃 기록만 있음. 있을수 없는 경우이므로, Line 삭제
					if(!tmpLogInDt.equals("") && tmpLogOutDt.equals("")){
						repLine = "";
					}
					
					// 로그인은 있고, 로그아웃 기록이 없으면, 2중 로그인 된것 이므로 로그아웃 기록 Update 및 새로운 로그인 기록
					if(!tmpLogInDt.equals("") && tmpLogOutDt.equals("")){
						

						StringBuilder sb = new StringBuilder();
						sb.append(tmpId + ",");
						sb.append(tmpIp + ",");
						sb.append(tmpLogInDt + ",");
						
						repLine = sb.toString();
					}
					
				} else {
					
					// 유저기록이 없는 Line 이므로, 전혀상관없는사람의 정보이니 건들지 않음.
					repLine = line;
				}

				// Line 정보 Update
				bufferedWriter.write(repLine, 0, repLine.length());
				bufferedWriter.newLine();
			}
			
			
			// 로그인 정보 작성
			StringBuilder sb = new StringBuilder();
			sb.append(userId + ",");
			sb.append(userIp + ",");
			sb.append("");
			
			
			bufferedWriter.write(sb.toString(), 0, sb.toString().length());
			bufferedWriter.newLine();
			
			// 정상적으로 수행되었음을 알리는 flag
			result = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제. 개별적으로 해제한다.
			try {
				bufferedReader.close();
			} catch (IOException ex1) {
				ex1.printStackTrace();
			}
			try {
				bufferedWriter.close();
			} catch (IOException ex2) {
				ex2.printStackTrace();
			}

			// 정상적으로 수행되었을 경우 원본 파일을 지우고 새로운 파일명을 원본파일명으로 rename한다.
			if (result) {
				inputFile.delete();
				outputFile.renameTo(new File(fileName));
			}
		}
	}
	
	public void logOutWrite(String fileName, String userId, String userIp) {

		// 원본파일경로

		// fileName =
		// "D:\\app\\tomcat\\fileStorage\\logs\\MOMENT_LOGIN_20150224.log";

		// file 객체 생성
		File inputFile = new File(fileName);
		File outputFile = new File(fileName + ".temp");

		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;

		boolean result = false;

		try {
			// FileInputStream,FileOutputStream, BufferdReader,
			// BufferedWriter
			// 생성
			fileInputStream = new FileInputStream(inputFile);
			fileOutputStream = new FileOutputStream(outputFile);

			bufferedReader = new BufferedReader(new InputStreamReader(
					fileInputStream));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					fileOutputStream));

			// 원본 파일에서 읽어 들이는 한라인
			String line;
			// 패턴에 일치하는 문자로 대체하고 난 후의 string
			String repLine;

			// 원본 파일에서 한라인씩 읽는다.
			while ((line = bufferedReader.readLine()) != null) {
				// 일치하는 패턴에서는 바꿀 문자로 변환

				String[] tmpArr = line.split(",", 4);
				String tmpId = tmpArr[0];
				String tmpIp = tmpArr[1];
				String tmpLogInDt = tmpArr[2];
				String tmpLogOutDt = tmpArr[3];

				System.out.println(line.toString());

				// 유저의 로그중, 로그인은 했고 로그아웃 기록이 없는것 검색
				if (userId.equals(tmpId) && !tmpLogInDt.equals("") && tmpLogOutDt.equals("")) {

					StringBuilder sb = new StringBuilder();
					sb.append(tmpId + ",");
					sb.append(tmpIp + ",");
					sb.append(tmpLogInDt + ",");
					sb.append(tmpLogOutDt);

					repLine = sb.toString();
				} else {
					repLine = line;
				}

				// 새로운 파일에 쓴다.
				bufferedWriter.write(repLine, 0, repLine.length());
				bufferedWriter.newLine();
			}
			// 정상적으로 수행되었음을 알리는 flag
			result = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제. 개별적으로 해제한다.
			try {
				bufferedReader.close();
			} catch (IOException ex1) {
				ex1.printStackTrace();
			}
			try {
				bufferedWriter.close();
			} catch (IOException ex2) {
				ex2.printStackTrace();
			}

			// 정상적으로 수행되었을 경우 원본 파일을 지우고 새로운 파일명을 원본파일명으로 rename한다.
			if (result) {
				inputFile.delete();
				outputFile.renameTo(new File(fileName));
			}
		}

	}
	
}
