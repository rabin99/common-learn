package datastream.eventtime;

import java.io.Serializable;

/**
 * @author linjh
 * @date 2019/2/16 16:49
 */
public class MyEvent implements Serializable {
    private String id;
    private Long eventTime;
    private String info;

    public MyEvent(String id, Long eventTime, String info) {
        this.id = id;
        this.eventTime = eventTime;
        this.info = info;
    }

    public MyEvent() {
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "id='" + id + '\'' +
                ", eventTime=" + eventTime +
                ", info='" + info + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
