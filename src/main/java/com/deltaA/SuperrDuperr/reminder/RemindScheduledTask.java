package com.deltaA.SuperrDuperr.reminder;


import com.deltaA.SuperrDuperr.entity.ToDoListItem;
import com.deltaA.SuperrDuperr.repository.ToDoListItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class RemindScheduledTask {
    @Autowired
    ToDoListItemRepository toDoListItemRepository;

    private static final int MILLIS_PER_DAY = 24*60*60*1000;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private static final Logger logger = LoggerFactory.getLogger(RemindScheduledTask.class);

    @Scheduled(fixedDelay = 50000)
    public void remindForTask() {

        List<ToDoListItem> toDoListItems = toDoListItemRepository.findByStatusCompleted();

        if (toDoListItems.size() > 0) {
            for (ToDoListItem toDoListItem : toDoListItems) {
                if (toDoListItem.getItemDueDate().equals(new Date()) || (toDoListItem.getItemDueDate().after(new Date()) && isSameDay(toDoListItem.getItemDueDate(), new Date()))) {
                    logger.info(" Reminder for task " + toDoListItem.getItemName() + " should be started.", dateFormat.format(new Date()));
                }
            }
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {

        // Strip out the time part of each date.
        long julianDayNumber1 = date1.getTime() / MILLIS_PER_DAY;
        long julianDayNumber2 = date2.getTime() / MILLIS_PER_DAY;

        // If they now are equal then it is the same day.
        return julianDayNumber1 == julianDayNumber2;
    }
}