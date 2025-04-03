package com.simbirsoft.taskone.service;

import com.simbirsoft.taskone.model.Customer;

import java.util.Comparator;
import java.util.List;

public class CustomerService {

    public Customer getClosestToAverageCustomer(List<Customer> customers) {
        if (customers != null && customers.isEmpty()) {
            return null;
        }

        // Вычисляем среднюю длину имени
        double averageLength = customers.stream()
                .mapToInt(c -> c.getName().length())
                .average()
                .orElse(0);

        // Находим клиента с длиной имени, наиболее близкой к среднему
        return customers.stream()
                .min(Comparator.comparingDouble(c -> Math.abs(c.getName().length() - averageLength)))
                .get();
    }
}

