package org.example.util;

import org.example.model.Meal;
import org.example.to.MealTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class MealUtil {


    public static void main(String[] args) {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Breakfast", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Lunch", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Dinner", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "util", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Breakfast", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Lunch", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Dinner", 410)
        );

        List<MealTO> mealTOS = filterByCycle(meals, LocalTime.of(8, 0), LocalTime.of(13, 0), 2000);
    mealTOS.forEach(System.out::println);
    }

    public static List<MealTO> filterByCycle (List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesByDay) {
        Map<LocalDate, Integer> mealMap = new HashMap<>();
        for (Meal meal : meals) {
            mealMap.put(meal.getDateTime().toLocalDate(), mealMap.getOrDefault(meal.getDateTime().toLocalDate(), 0) + meal.getCalories());
        }

        List<MealTO> result = new ArrayList<>();
        for (Meal meal : meals) {
            if (DateTimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                result.add(new MealTO(meal.getDateTime(), meal.getDescription(), meal.getCalories(), mealMap.get(meal.getDateTime().toLocalDate()) > caloriesByDay));
            }
        }

        return result;
    }
}
