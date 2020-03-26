package entity;

import java.util.Map;

public class BaseMessage {

	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String MsgType;

	public BaseMessage(Map<String,String> requestMap){
		this.toUserName = requestMap.get("fromUserName");
		this.fromUserName = requestMap.get("toUserName");
		this.createTime = System.currentTimeMillis()/1000 + "";
	}
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
