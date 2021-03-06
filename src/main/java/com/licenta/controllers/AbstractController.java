package com.licenta.controllers;

import com.licenta.dto.UserDTO;
import com.licenta.models.User;
import com.licenta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public abstract class AbstractController {

    static final String EXERCISE_TEMPLATE = "exercise";
    static final String REPORT_TEMPLATE = "report";

    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    UserService userService;

    /**
     * Used by child class controllers to obtain the currently authenticated user from Spring Security.
     */

    final User currentAuthenticatedUser(final HttpServletRequest request) {
        return userService.findByEmailEquals((String) request.getAttribute("email"));
    }

    final java.sql.Date stringToSqlDate(final String dateString) {
        java.sql.Date date;
        try {
            date = java.sql.Date.valueOf(dateString);
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
            date = new java.sql.Date(new Date().getTime());
        }
        return date;
    }

    final java.sql.Date todaySqlDateForUser(final UserDTO user) {
        if (user == null) {
            return new java.sql.Date(new Date().getTime());
        } else {
            final ZoneId timeZone = TimeZone.getDefault().toZoneId();
            final ZonedDateTime zonedDateTime = ZonedDateTime.now(timeZone);
            return new java.sql.Date(zonedDateTime.toLocalDate().atStartOfDay(timeZone).toInstant().toEpochMilli());
        }
    }

}