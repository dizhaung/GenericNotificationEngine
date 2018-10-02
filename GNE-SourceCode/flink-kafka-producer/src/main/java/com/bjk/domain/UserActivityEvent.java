package com.bjk.domain;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *  
 * @author Yogesh Gaikwad
 *
 */
public class UserActivityEvent implements Serializable {

	private Long id;

	private EventType eventType;

	private String date;

	private String correlationId;

	private String clientIp;

	private String userAgent;

	private String userAgentFiltered;

	private String details;

	private String reference;

	private String notificationType;

	private String toEmailId;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setCorrelationId(final String correlationId) {
		this.correlationId = correlationId;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(final String clientIp) {
		this.clientIp = clientIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(final String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserAgentFiltered() {
		return userAgentFiltered;
	}

	public void setUserAgentFiltered(final String userAgentFiltered) {
		this.userAgentFiltered = userAgentFiltered;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(final EventType eventType) {
		this.eventType = eventType;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getToEmailId() {
		return toEmailId;
	}

	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}

	@JsonIgnore
	public boolean isValid() {
		return eventType != null && !StringUtils.isBlank(correlationId)
				&& date != null;
	}

	public UserActivityEvent(final Long id, final EventType eventType,
			final String date, final String correlationId,
			final String clientIp, final String userAgent,
			final String userAgentFiltered, final String details,
			final String reference, final String notificationType,
			final String toEmailId) {
		this();
		setId(id);
		setEventType(eventType);
		setDate(date);
		setCorrelationId(correlationId);
		setClientIp(clientIp);
		setUserAgent(userAgent);
		setUserAgentFiltered(userAgentFiltered);
		setDetails(details);
		setReference(reference);
		setNotificationType(notificationType);
		setToEmailId(toEmailId);
	}

	public UserActivityEvent() {
	}

	@Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserActivityEvent)) {
      return false;
    }
    final UserActivityEvent userActivityEvent = (UserActivityEvent) o;
    return Objects.equals(getId(), userActivityEvent.getId()) &&
        getEventType() == userActivityEvent.getEventType() &&
        Objects.equals(getDate(), userActivityEvent.getDate()) &&
        Objects.equals(getCorrelationId(), userActivityEvent.getCorrelationId()) &&
        Objects.equals(getClientIp(), userActivityEvent.getClientIp()) &&
        Objects.equals(getUserAgent(), userActivityEvent.getUserAgent()) &&
        Objects.equals(getUserAgentFiltered(), userActivityEvent.getUserAgentFiltered()) &&
        Objects.equals(getDetails(), userActivityEvent.getDetails()) &&
        Objects.equals(getReference(), userActivityEvent.getReference()) &&
        Objects.equals(getNotificationType(), userActivityEvent.getNotificationType()) &&
        Objects.equals(getToEmailId(), userActivityEvent.getToEmailId());
  }

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getEventType(), getDate(),
				getCorrelationId(), getClientIp(), getUserAgent(),
				getUserAgentFiltered(), getDetails(), getReference(),
				getNotificationType(), getToEmailId());
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id)
				.append("eventType", eventType).append("date", date)
				.append("correlationId", correlationId)
				.append("clientIp", clientIp).append("userAgent", userAgent)
				.append("userAgentFiltered", userAgentFiltered)
				.append("details", details).append("reference", reference)
				.append("notificationType", notificationType)
				.append("toEmailId", toEmailId).toString();
	}
}
