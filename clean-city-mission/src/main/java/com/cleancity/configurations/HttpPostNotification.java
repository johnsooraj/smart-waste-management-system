package com.cleancity.configurations;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import com.cleancity.wastebin.document.WasteBin;

@Configuration
public class HttpPostNotification {

	static Logger logger = Logger.getLogger(HttpPostNotification.class.getName());

	HttpClient httpclient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	JSONObject body;
	JSONObject notification;

	static HttpPostNotification obj;

	@Autowired
	Environment environment;

	public HttpPostNotification() {
		this.body = new JSONObject();
		this.notification = new JSONObject();
		this.httpclient = HttpClients.createDefault();
		this.httppost = new HttpPost("https://fcm.googleapis.com/fcm/send");
		this.httppost.addHeader("Content-Type", "application/json");
		this.httppost.addHeader("Authorization", "key=AIzaSyDrHgg_IWBW76_F5mN9ACv2OnfRPZ8yiYQ");
	}

	public JSONObject getBody() {
		return body;
	}

	public void buildNotificationBody(String token, WasteBin bin) {

		JSONObject binData = new JSONObject();
		binData.put("id", bin.getId());
		binData.put("binCurrentCapacity", bin.getBinCurrentCapacity());
		binData.put("pincode", bin.getPincode());
		binData.put("latitude", bin.getLatitude());
		binData.put("longitude", bin.getLongitude());
		binData.put("lastFilled", bin.getTimestamp());

		this.body.put("to", token);
		this.notification.put("title", "Clean City Alert");
		this.notification.put("body", binData);
		this.notification.put("icon", "https://static.thenounproject.com/png/36770-200.png");
		this.notification.put("click_action", true);
		this.notification.put("content_available", true);
	}

	public void buildNotificationBody(String token, List<WasteBin> bin) {

		this.body.put("to", token);
		this.notification.put("title", "Clean City Alert");
		this.notification.put("body", bin);
		this.notification.put("icon", "https://static.thenounproject.com/png/36770-200.png");
		this.notification.put("click_action", true);
		this.notification.put("content_available", true);
	}

	public void setTopic(String to) {
		this.body.put("to", "/topics/" + to);
	}

	public void setToken(String to) {
		this.body.put("to", to);
	}

	public void setTitle(String title) {
		this.notification.put("title", title);
	}

	public void setBody(String body) {
		this.notification.put("body", body);
	}

	public void setIcon(String icon) {
		this.notification.put("icon", icon);
	}

	public void setClickAction(String c_action) {
		this.notification.put("click_action", c_action);
	}

	public void setNotificationType(String type) {
		this.notification.put("notificationType", type);
	}

	public void setContentAvailable(boolean status) {
		this.notification.put("content_available", status);
	}

	public boolean sendNotification() {
		try {
			this.body.put("notification", notification);
			HttpEntity stringEntity = new StringEntity(body.toString(), ContentType.APPLICATION_JSON);
			this.httppost.setEntity(stringEntity);
			HttpResponse response = httpclient.execute(httppost);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.OK.value()) {
				HttpEntity entity = response.getEntity();
				String content = EntityUtils.toString(entity);
				logger.info("notification send success" + content);
				System.out.println(notification);
				return true;
			}
			if (statusLine.getStatusCode() == HttpStatus.BAD_REQUEST.value()) {
				logger.error("notification send failed");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
