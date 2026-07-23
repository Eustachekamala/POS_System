package com.eustache.pos_system.DTO.Stock.Response;

import java.time.LocalDate;

public record StockSummary(
        Double quantity,
        Integer minQuantity,
        LocalDate lastUpdated
) {
}
