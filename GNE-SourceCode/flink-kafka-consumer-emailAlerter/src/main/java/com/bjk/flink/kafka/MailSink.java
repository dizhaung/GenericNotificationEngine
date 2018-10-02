package com.bjk.flink.kafka;

import java.sql.SQLException;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class set all the parameters required for mail to be sent.
 * 
 * @author Yogesh Gaikwad
 *
 */
public class MailSink extends RichSinkFunction<JSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4999470984152771482L;
	private static Logger logger = LoggerFactory.getLogger("EMAIL_ALERTER_STREAMING");

	private String filePath;
	MailRunner mr;
	public MailSink() {
	}

	public MailSink(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void open(Configuration parameters) throws Exception {
		logger.info(100 + " - " + "Initializing Property File");
		mr = new MailRunner();
	}

	@Override
	public void close() throws SQLException {
	}

	@Override
	public void invoke(JSONObject alert) throws Exception {
		try {
			/**
			 * Alert event must not be logged as it contains some sensitive
			 * information.
			 */
			String toEmailId = alert.get("toEmailId").toString();
			
			mr.postMail(toEmailId);
		
		} catch (Exception e) {
			logger.error("Error getting the Mail parameters. Event is : " + alert.toString());
			logger.error(400 + " - " + "Exception is : ", e);
		}
	}
}
