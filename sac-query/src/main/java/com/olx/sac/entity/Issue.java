package com.olx.sac.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotBlank
    private String description;

    @NotBlank
    private String reason;

    @NotBlank
    private String state;

    @NotBlank
    private String type;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public int getDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdAt);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equal(description, issue.description) &&
                Objects.equal(reason, issue.reason) &&
                Objects.equal(state, issue.state) &&
                Objects.equal(type, issue.type) &&
                Objects.equal(createdAt, issue.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description, reason, state, type, createdAt);
    }
}